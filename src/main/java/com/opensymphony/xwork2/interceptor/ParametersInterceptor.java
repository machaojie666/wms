//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.opensymphony.xwork2.interceptor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ValidationAware;
import com.opensymphony.xwork2.inject.Inject;
import com.opensymphony.xwork2.security.AcceptedPatternsChecker;
import com.opensymphony.xwork2.security.ExcludedPatternsChecker;
import com.opensymphony.xwork2.security.AcceptedPatternsChecker.IsAccepted;
import com.opensymphony.xwork2.security.ExcludedPatternsChecker.IsExcluded;
import com.opensymphony.xwork2.util.ClearableValueStack;
import com.opensymphony.xwork2.util.LocalizedTextUtil;
import com.opensymphony.xwork2.util.MemberAccessValueStack;
import com.opensymphony.xwork2.util.ValueStack;
import com.opensymphony.xwork2.util.ValueStackFactory;
import com.opensymphony.xwork2.util.logging.Logger;
import com.opensymphony.xwork2.util.logging.LoggerFactory;
import com.opensymphony.xwork2.util.reflection.ReflectionContextState;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;

public class ParametersInterceptor extends MethodFilterInterceptor {
    private static final Logger LOG = LoggerFactory.getLogger(ParametersInterceptor.class);
    protected static final int PARAM_NAME_MAX_LENGTH = 100;
    private int paramNameMaxLength = 100;
    private boolean devMode = false;
    protected boolean ordered = false;
    private ValueStackFactory valueStackFactory;
    private ExcludedPatternsChecker excludedPatterns;
    private AcceptedPatternsChecker acceptedPatterns;
    static final Comparator<String> rbCollator = new Comparator<String>() {
        public int compare(String s1, String s2) {
            int l1 = ParametersInterceptor.countOGNLCharacters(s1);
            int l2 = ParametersInterceptor.countOGNLCharacters(s2);
            return l1 < l2?-1:(l2 < l1?1:s1.compareTo(s2));
        }
    };

    public ParametersInterceptor() {
    }

    @Inject
    public void setValueStackFactory(ValueStackFactory valueStackFactory) {
        this.valueStackFactory = valueStackFactory;
    }

    @Inject("devMode")
    public void setDevMode(String mode) {
        this.devMode = "true".equalsIgnoreCase(mode);
    }

    @Inject
    public void setExcludedPatterns(ExcludedPatternsChecker excludedPatterns) {
        this.excludedPatterns = excludedPatterns;
    }

    @Inject
    public void setAcceptedPatterns(AcceptedPatternsChecker acceptedPatterns) {
        this.acceptedPatterns = acceptedPatterns;
    }

    public void setParamNameMaxLength(int paramNameMaxLength) {
        this.paramNameMaxLength = paramNameMaxLength;
    }

    private static int countOGNLCharacters(String s) {
        int count = 0;

        for(int i = s.length() - 1; i >= 0; --i) {
            char c = s.charAt(i);
            if(c == 46 || c == 91) {
                ++count;
            }
        }

        return count;
    }

    public String doIntercept(ActionInvocation invocation) throws Exception {
        Object action = invocation.getAction();
        if(!(action instanceof NoParameters)) {
            ActionContext ac = invocation.getInvocationContext();
            Map<String, Object> parameters = this.retrieveParameters(ac);
            if(LOG.isDebugEnabled()) {
                LOG.debug("Setting params " + this.getParameterLogMap(parameters), new String[0]);
            }

            if(parameters != null) {
                Map contextMap = ac.getContextMap();

                try {
                    ReflectionContextState.setCreatingNullObjects(contextMap, true);
                    ReflectionContextState.setDenyMethodExecution(contextMap, true);
                    ReflectionContextState.setReportingConversionErrors(contextMap, true);
                    ValueStack stack = ac.getValueStack();
                    this.setParameters(action, stack, parameters);
                } finally {
                    ReflectionContextState.setCreatingNullObjects(contextMap, false);
                    ReflectionContextState.setDenyMethodExecution(contextMap, false);
                    ReflectionContextState.setReportingConversionErrors(contextMap, false);
                }
            }
        }

        return invocation.invoke();
    }

    protected Map<String, Object> retrieveParameters(ActionContext ac) {
        return ac.getParameters();
    }

    protected void addParametersToContext(ActionContext ac, Map<String, Object> newParams) {
    }

    protected void setParameters(Object action, ValueStack stack, Map<String, Object> parameters) {
        TreeMap params;
        TreeMap acceptableParameters;
        if(this.ordered) {
            params = new TreeMap(this.getOrderedComparator());
            acceptableParameters = new TreeMap(this.getOrderedComparator());
            params.putAll(parameters);
        } else {
            params = new TreeMap(parameters);
            acceptableParameters = new TreeMap();
        }

        Iterator i$ = params.entrySet().iterator();

        while(i$.hasNext()) {
            Entry<String, Object> entry = (Entry)i$.next();
            String name = (String)entry.getKey();
            Object value = entry.getValue();
            if(this.isAcceptableParameter(name, action) && this.isAcceptableValue(value)) {
                acceptableParameters.put(name, entry.getValue());
            }
        }

        ValueStack newStack = this.valueStackFactory.createValueStack(stack);
        boolean clearableStack = newStack instanceof ClearableValueStack;
        if(clearableStack) {
            ((ClearableValueStack)newStack).clearContextValues();
            Map<String, Object> context = newStack.getContext();
            ReflectionContextState.setCreatingNullObjects(context, true);
            ReflectionContextState.setDenyMethodExecution(context, true);
            ReflectionContextState.setReportingConversionErrors(context, true);
            context.put("com.opensymphony.xwork2.ActionContext.locale", stack.getContext().get("com.opensymphony.xwork2.ActionContext.locale"));
        }

        boolean memberAccessStack = newStack instanceof MemberAccessValueStack;
        if(memberAccessStack) {
            MemberAccessValueStack accessValueStack = (MemberAccessValueStack)newStack;
            accessValueStack.setAcceptProperties(this.acceptedPatterns.getAcceptedPatterns());
            accessValueStack.setExcludeProperties(this.excludedPatterns.getExcludedPatterns());
        }

        i$ = acceptableParameters.entrySet().iterator();

        while(i$.hasNext()) {
            Entry<String, Object> entry = (Entry)i$.next();
            String name = (String)entry.getKey();
            Object value = entry.getValue();

            try {
                newStack.setParameter(name, value);
            } catch (RuntimeException var14) {
                if(this.devMode) {
                    // this.notifyDeveloperParameterException(action, name, var14.getMessage());
                }
            }
        }

        if(clearableStack && stack.getContext() != null && newStack.getContext() != null) {
            stack.getContext().put("com.opensymphony.xwork2.ActionContext.conversionErrors", newStack.getContext().get("com.opensymphony.xwork2.ActionContext.conversionErrors"));
        }

        this.addParametersToContext(ActionContext.getContext(), acceptableParameters);
    }

    protected void notifyDeveloperParameterException(Object action, String property, String message) {
        String developerNotification = LocalizedTextUtil.findText(ParametersInterceptor.class, "devmode.notification", ActionContext.getContext().getLocale(), "Developer Notification:\n{0}", new Object[]{"Unexpected Exception caught setting '" + property + "' on '" + action.getClass() + ": " + message});
        LOG.error(developerNotification, new String[0]);
        if(action instanceof ValidationAware) {
            Collection<String> messages = ((ValidationAware)action).getActionMessages();
            messages.add(message);
            ((ValidationAware)action).setActionMessages(messages);
        }

    }

    protected boolean isAcceptableParameter(String name, Object action) {
        ParameterNameAware parameterNameAware = action instanceof ParameterNameAware?(ParameterNameAware)action:null;
        return this.acceptableName(name) && (parameterNameAware == null || parameterNameAware.acceptableParameterName(name));
    }

    protected boolean isAcceptableValue(Object value) {
        if(value == null) {
            return true;
        } else {
            Object[] values;
            if(value.getClass().isArray()) {
                values = (Object[])((Object[])value);
            } else {
                values = new Object[]{value};
            }

            boolean result = true;
            Object[] arr$ = values;
            int len$ = values.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                Object obj = arr$[i$];
                if(this.isExcluded(String.valueOf(obj))) {
                    result = false;
                }
            }

            return result;
        }
    }

    protected Comparator<String> getOrderedComparator() {
        return rbCollator;
    }

    protected String getParameterLogMap(Map<String, Object> parameters) {
        if(parameters == null) {
            return "NONE";
        } else {
            StringBuilder logEntry = new StringBuilder();
            Iterator i$ = parameters.entrySet().iterator();

            while(true) {
                while(i$.hasNext()) {
                    Entry entry = (Entry)i$.next();
                    logEntry.append(String.valueOf(entry.getKey()));
                    logEntry.append(" => ");
                    if(entry.getValue() instanceof Object[]) {
                        Object[] valueArray = (Object[])((Object[])entry.getValue());
                        logEntry.append("[ ");
                        if(valueArray.length > 0) {
                            for(int indexA = 0; indexA < valueArray.length - 1; ++indexA) {
                                Object valueAtIndex = valueArray[indexA];
                                logEntry.append(String.valueOf(valueAtIndex));
                                logEntry.append(", ");
                            }

                            logEntry.append(String.valueOf(valueArray[valueArray.length - 1]));
                        }

                        logEntry.append(" ] ");
                    } else {
                        logEntry.append(String.valueOf(entry.getValue()));
                    }
                }

                return logEntry.toString();
            }
        }
    }

    protected boolean acceptableName(String name) {
        boolean accepted = this.isWithinLengthLimit(name) && !this.isExcluded(name) && this.isAccepted(name);
        if(this.devMode && accepted) {
            LOG.debug("Parameter [#0] was accepted and will be appended to action!", new String[]{name});
        }

        return accepted;
    }

    protected boolean isWithinLengthLimit(String name) {
        boolean matchLength = name.length() <= this.paramNameMaxLength;
        if(!matchLength) {
            this.notifyDeveloper("Parameter [#0] is too long, allowed length is [#1]", new String[]{name, String.valueOf(this.paramNameMaxLength)});
        }

        return matchLength;
    }

    protected boolean isAccepted(String paramName) {
        IsAccepted result = this.acceptedPatterns.isAccepted(paramName);
        if(result.isAccepted()) {
            return true;
        } else {
            this.notifyDeveloper("Parameter [#0] didn't match accepted pattern [#1]!", new String[]{paramName, result.getAcceptedPattern()});
            return false;
        }
    }

    protected boolean isExcluded(String paramName) {
        IsExcluded result = this.excludedPatterns.isExcluded(paramName);
        if(result.isExcluded()) {
            this.notifyDeveloper("Parameter [#0] matches excluded pattern [#1]!", new String[]{paramName, result.getExcludedPattern()});
            return true;
        } else {
            return false;
        }
    }

    private void notifyDeveloper(String message, String... parameters) {
        if(this.devMode) {
            LOG.warn(message, parameters);
        } else if(LOG.isDebugEnabled()) {
            LOG.debug(message, parameters);
        }

    }

    public boolean isOrdered() {
        return this.ordered;
    }

    public void setOrdered(boolean ordered) {
        this.ordered = ordered;
    }

    public void setAcceptParamNames(String commaDelim) {
        this.acceptedPatterns.setAcceptedPatterns(commaDelim);
    }

    public void setExcludeParams(String commaDelim) {
        this.excludedPatterns.setExcludedPatterns(commaDelim);
    }
}
