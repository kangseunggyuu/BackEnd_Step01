package spms.controls;

import java.util.Map;

import spms.bind.DataBinding;
import spms.dao.MemberDao;

public class MemberDeleteController implements Controller, DataBinding {

	MemberDao memberDao;

	public MemberDeleteController setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
		return this;
	}
	
	@Override
	public Object[] getDataBinders() {
		// TODO Auto-generated method stub
		return new Object[] {
				"no", Integer.class
		};
	}

	@Override
	public String execute(Map<String, Object> model) throws Exception {
		System.out.println("MemberDeleteController::execute() - get 요청");

		// MemberDao memberDao = (MemberDao) model.get("memberDao");

		Integer no = (Integer) model.get("no");
		memberDao.delete(no);

		return "redirect:list.do";
	}	
}
