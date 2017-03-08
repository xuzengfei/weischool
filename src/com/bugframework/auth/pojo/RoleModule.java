package com.bugframework.auth.pojo;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bugframework.common.pojo.IdEntity;

@Entity
@Table(name="auth_r_m")
public class RoleModule extends IdEntity {

		private Role role;
		private Module module;
		@ManyToOne
		@JoinColumn(name="roleid",nullable=false)
		public Role getRole() {
			return role;
		}
		public void setRole(Role role) {
			this.role = role;
		}
		@ManyToOne
		@JoinColumn(name="moduleid",nullable=false)
		public Module getModule() {
			return module;
		}
		public void setModule(Module module) {
			this.module = module;
		}
		
		
}
