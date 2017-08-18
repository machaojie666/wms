package ${packageName}.web.action;

import ${packageName}.domain.${className};
import ${packageName}.query.${className}QueryObject;
import ${packageName}.service.I${className}Service;
import ${packageName}.domain.RequiredPermission;
import lombok.Getter;
import lombok.Setter;

public class ${className}Action extends BaseAction{
	private static final long serialVersionUID = 1L;

	@Setter 
	private I${className}Service service;

	@Getter 
	private ${className}QueryObject qo=new ${className}QueryObject();

	@Getter private ${className} ${objectName}=new ${className}();

	@RequiredPermission("${objectCNName}列表")
	public String execute(){
		try {
			put("result", service.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	@RequiredPermission("${objectCNName}编辑")
	public String input() {
		try {
			if (${objectName}.getId() != null) {
                ${objectName} = service.get(${objectName}.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("${objectCNName}保存/更新")
	public String saveOrUpdate() {
		try {
			if (${objectName}.getId() == null) {
                service.save(${objectName});
				addActionMessage("保存成功");
            } else {
                service.update(${objectName});
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("${objectCNName}删除")
	public String delete()  throws  Exception {
		try {
			if (${objectName}.getId() != null) {
                service.delete(${objectName}.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

}
