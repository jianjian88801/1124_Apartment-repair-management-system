const base = {
    get() {
        return {
            url : "http://localhost:8080/springboot3lx4p/",
            name: "springboot3lx4p",
            // 退出到首页链接
            indexUrl: ''
        };
    },
    getProjectName(){
        return {
            projectName: "公寓报修管理系统"
        } 
    }
}
export default base
