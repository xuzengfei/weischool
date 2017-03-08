package com.bugframework.common.pojo;

public class BaseTree {
    /**
     * 树的ID
     */
    private  String id;
    /**
     * 上级ID
     */
    private  String pId;
    /**
     * 名称
     */
    private  String name;
    /**
     * 访问url
     */
    private  String file;
    /**
     * 是否展开
     */
    private  boolean open  ;
    /**
     * 是否选中
     */
    private  boolean checked;
    /**
     * 是否可以删除
     */
    private  boolean canDel;
    /**
     * 第几层
     */
    private Short floor;

    private BaseTree(TreeBuilder builder){
        this.pId=builder.pId;
        this.id=builder.id;
        this.name=builder.name;
        this.canDel =builder.canDel;
        this.checked=builder.checked;
        this.file=builder.file;
        this.open=builder.open;
        this.floor=builder.floor;
    }
    public static class TreeBuilder{
        /**
         * 树的ID
         */
        private final String id;
        /**
         * 上级ID
         */
        private final String pId;
        /**
         * 名称
         */
        private  final String name;
        /**
         * 访问url
         */
        private String file;
        /**
         * 是否展开
         */
        private boolean open = false;
        /**
         * 是否选中
         */
        private boolean checked = false;
        /**
         * 是否可以删除
         */
        private boolean canDel;
        /**
         * 第几层
         */
        private Short floor;


        public TreeBuilder(String pid,String id,String name){
            this.pId =pid;
            this.id =id;
            this.name =name;
        }
        public  TreeBuilder file(String file){
            this.file=file;
            return this;
        }
        public  TreeBuilder open(boolean open){
            this.open=open;
            return this;
        }
        public  TreeBuilder checked(boolean checked){
            this.checked=checked;
            return this;
        }
        public  TreeBuilder canDel(boolean canDel){
            this.canDel=canDel;
            return this;
        }
        public  TreeBuilder floor(Short floor){
            this.floor=floor;
            return this;
        }
        public BaseTree build(){
            return  new BaseTree(this);
        }

    }

   public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isCanDel() {
        return canDel;
    }

    public void setCanDel(boolean canDel) {
        this.canDel = canDel;
    }


    public Short getFloor() {
        return floor;
    }


    public void setFloor(Short floor) {
        this.floor = floor;
    }

}
