package cn.seu.cose.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import oracle.net.aso.a;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Component;

import cn.seu.cose.entity.Designer;

import com.ibatis.sqlmap.client.SqlMapClient;

@SuppressWarnings("unchecked")
@Component
public class DesignerDAOImpl extends SqlMapClientDaoSupport implements
		DesignerDAO {

	@Autowired(required = true)
	public void setSqlMapClientTemp(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}

	@Override
	public List<Designer> getAllDesigners() {
		return getSqlMapClientTemplate().queryForList(
				"DESIGNER.selectAllDesigners");
	}

	@Override
	public List<Designer> getAllCertificatedDesigners() {
		return getSqlMapClientTemplate().queryForList(
				"DESIGNER.selectAllCertificatedDesigners");
	}

	public List<Designer> getAllUncertificatedDesigners() {
		return getSqlMapClientTemplate().queryForList(
				"DESIGNER.selectAllUncertificatedDesigners");
	}

	@Override
	public Designer getDesignerById(int id) {
		return (Designer) getSqlMapClientTemplate().queryForObject(
				"DESIGNER.selectDesignerById", id);
	}

	@Override
	public Designer getDesignerByUsernameAndPswd(String username, String pswd) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userName", username);
		map.put("password", pswd);
		return (Designer) getSqlMapClientTemplate().queryForObject(
				"DESIGNER.selectDesignerByUserNameAndPswd", map);
	}

	@Override
	public List<Designer> searchDesignerByName(String searchInput) {
		return getSqlMapClientTemplate().queryForList(
				"DESIGNER.searchDesignerByName", searchInput);
	}

	@Override
	public void insertDesigner(Designer designer) {
		getSqlMapClientTemplate().insert("DESIGNER.insertDesigner", designer);
	}

	@Override
	public void certificateDesignerById(int id) {
		getSqlMapClientTemplate()
				.update("DESIGNER.certificateDesignerById", id);
	}

	@Override
	public void uncertificateDesignerById(int id) {
		getSqlMapClientTemplate().update("DESIGNER.uncertificateDesignerById",
				id);
	}

	@Override
	public void updateDesigner(Designer designer) {
		getSqlMapClientTemplate().update("DESIGNER.updateDesigner", designer);
	}

	@Override
	public Designer getDesignerByName(String name) {
		return (Designer) getSqlMapClientTemplate().queryForObject(
				"DESIGNER.selectDesignerByName", name);
	}

	@Override
	public void deleteDesigner(int id) {
		getSqlMapClientTemplate().delete("DESIGNER.deleteDesigner", id);
	}

	@Override
	public Designer getDesignerByKey(String key) {
		return (Designer) getSqlMapClientTemplate().queryForObject(
				"DESIGNER.selectDesignerByKey", key);
	}

	@Override
	public List<Designer> getAllDesignersByBaseAndRange(int base, int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"DESIGNER.selectAllDesignersByBaseAndRange", map);
	}

	@Override
	public int getAllDesignerCount() {
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"DESIGNER.selectAllDesignerCount");
	}

	@Override
	public List<Designer> getTypeDesignersByBaseAndRange(int type, int base,
			int range) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("type", type);
		map.put("base", base);
		map.put("range", range);
		return getSqlMapClientTemplate().queryForList(
				"DESIGNER.selectTypeDesignersByBaseAndRange", map);
	}

	@Override
	public int getTypeDesignerCount(int type) {
		return (Integer)getSqlMapClientTemplate().queryForObject(
				"DESIGNER.selectTypeDesignerCount", type);
	}
}
