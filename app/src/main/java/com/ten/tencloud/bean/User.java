package com.ten.tencloud.bean;

import java.util.List;

/**
 * Created by lxq on 2017/11/21.
 */

public class User {

    private String loginName;
    private String token;
    private String name;
    private String phone;
    private String email;
    private String office;

    public User() {
    }

    public User(String mobile, String password) {
        this.mobile = mobile;
        this.password = password;
    }

    private String mobile;
    private String password;

    private List<Role> role;

    public static class Role{
        private String menuId;
        private String permission;

        public String getMenuId() {
            return menuId;
        }

        public void setMenuId(String menuId) {
            this.menuId = menuId;
        }

        public String getPermission() {
            return permission;
        }

        public void setPermission(String permission) {
            this.permission = permission;
        }
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }
}
