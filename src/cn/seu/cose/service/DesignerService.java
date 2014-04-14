package cn.seu.cose.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.seu.cose.dao.BlogDAO;
import cn.seu.cose.dao.CommentDAO;
import cn.seu.cose.dao.DesignerDAO;
import cn.seu.cose.entity.Designer;
import cn.seu.cose.filter.SecurityContextHolder;
import cn.seu.cose.util.Constant.CommentType;

@Service
public class DesignerService {

	@Autowired
	DesignerDAO designerDAOImpl;
	@Autowired
	CommentDAO commentDAOImpl;
	@Autowired
	BlogDAO blogDAOImpl;

	public List<Designer> getPopularDesigner() {
		List<Integer> ids = commentDAOImpl.rankCommentCountWithType(
				CommentType.DESIGNER.ordinal(), 6);
		List<Designer> designers = new ArrayList<Designer>();
		for (int id : ids) {
			Designer designer = designerDAOImpl.getDesignerById(id);
			if (designer != null) {
				designers.add(designer);
			}
		}
		return designers;
	}

	public List<Designer> getHardWorkingDesigners(int topN) {
		List<Integer> ids = blogDAOImpl.rankDesignerWithBlogCount(topN);
		List<Designer> designers = new ArrayList<Designer>();
		for (int id : ids) {
			Designer designer = designerDAOImpl.getDesignerById(id);
			if (designer != null) {
				designers.add(designer);
			}
		}
		return designers;
	}

	public List<Designer> getAllDesigners() {
		return designerDAOImpl.getAllDesigners();
	}

	public List<Designer> getAllCertificatedDesigners() {
		return designerDAOImpl.getAllCertificatedDesigners();
	}

	public List<Designer> getAllUncertificatedDesigners() {
		return designerDAOImpl.getAllUncertificatedDesigners();
	}

	public Designer getDesignerById(int id) {
		return designerDAOImpl.getDesignerById(id);
	}

	public Designer getDesignerByName(String username) {
		return designerDAOImpl.getDesignerByName(username);
	}

	public Designer getDesignerByUsernameAndPswd(String username, String pwd) {
		return designerDAOImpl.getDesignerByUsernameAndPswd(username, pwd);
	}

	public List<Designer> searchDesignerByName(String searchInput) {
		return designerDAOImpl.searchDesignerByName(searchInput);
	}

	public void insertDesigner(Designer designer) {
		designerDAOImpl.insertDesigner(designer);
	}

	public void certificateDesignerById(int id) {
		designerDAOImpl.certificateDesignerById(id);
	}

	public void uncertificateDesignerById(int id) {
		designerDAOImpl.uncertificateDesignerById(id);
	}

	public void updateDesigner(Designer designer) {
		designerDAOImpl.updateDesigner(designer);
	}

	public void deleteDesigner(int id) {
		designerDAOImpl.deleteDesigner(id);
	}

	/**
	 * designer sign in
	 */
	public Designer signIn(String name, String pswd) {
		Designer designer = designerDAOImpl.getDesignerByUsernameAndPswd(name,
				pswd);
		SecurityContextHolder.getSecurityContext().setDesigner(designer);
		return designer;
	}

	/**
	 * designer log off
	 */
	public void logOff() {
		SecurityContextHolder.getSecurityContext().setDesigner(null);
	}

	/**
	 * return whether the current user sign in or not
	 * 
	 * @return
	 */
	public boolean isSignIn() {
		Designer designer = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		if (designer == null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean isTheSignInOne(int designerId) {
		Designer designer = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		if (designer == null || designer.getId() != designerId) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * get the current designer who has signed in
	 */
	public Designer getCurrentUser() {
		Designer designer = SecurityContextHolder.getSecurityContext()
				.getDesigner();
		return designer;
	}
}
