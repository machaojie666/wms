package com._520it.wms.web.action;

import com._520it.wms.domain.Product;
import com._520it.wms.query.ProductQueryObject;
import com._520it.wms.service.IBrandService;
import com._520it.wms.service.IProductService;
import com._520it.wms.domain.RequiredPermission;
import com._520it.wms.util.FileUploadUtil;
import com.alibaba.druid.util.StringUtils;
import lombok.Getter;
import lombok.Setter;

import java.io.File;

public class ProductAction extends BaseAction {
    private static final long serialVersionUID = 1L;

    @Setter
    private IProductService service;
    @Setter
    private IBrandService brandService;
    @Getter
    private ProductQueryObject qo = new ProductQueryObject();
    @Getter
    private Product product = new Product();
    @Setter
    private File pic;
    @Setter
    private String picFileName;

    @RequiredPermission("商品管理列表")
    public String execute() {
        try {
            put("brands", brandService.list());
            put("result", service.pageQuery(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("查询失败");
        }
        return LIST;
    }
    @RequiredPermission("商品列表")
    public String selectProductList() {
        try {
            put("brands", brandService.list());
            put("result", service.pageQuery(qo));
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("查询失败");
        }
        return "selectProductList";
    }

    @RequiredPermission("商品管理编辑")
    public String input() {
        try {
            put("brands", brandService.list());
            if (product.getId() != null) {
                product = service.get(product.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("跳转失败");
        }
        return INPUT;
    }

    @RequiredPermission("商品管理保存/更新")
    public String saveOrUpdate() {
        try {
            // 如果更新时重新选择了图片
            if (pic != null) {
                if (!StringUtils.isEmpty(product.getImagePath())) {
                    FileUploadUtil.deleteFile(product.getImagePath());
                }
                // 更新时,删除之前的图片,再保存更新后的图片
                // 上传图片
                String imagePath = FileUploadUtil.uploadFile(pic, picFileName);
                product.setImagePath(imagePath);
            }
            if (product.getId() == null) {
                service.save(product);
                addActionMessage("保存成功");
            } else {
                service.update(product);
                addActionMessage("更新成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("保存或更新失败");
        }
        return SUCCESS;
    }

    @RequiredPermission("商品管理删除")
    public String delete() throws Exception {
        try {
            if (product.getId() != null) {
                if (product.getImagePath() != null) {
                    FileUploadUtil.deleteFile(product.getImagePath());
                }

                service.delete(product.getId());
                putMsg("删除成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            putMsg("删除失败");
        }
        return NONE;
    }

}
