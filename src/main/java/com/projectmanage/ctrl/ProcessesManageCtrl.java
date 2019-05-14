package com.projectmanage.ctrl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.projectmanage.common.ItemAreaType;
import com.projectmanage.common.ItemSetEnable;
import com.projectmanage.common.ItemSetSysType;
import com.projectmanage.common.ItemSetType;
import com.projectmanage.common.ItemSetUnit;
import com.projectmanage.common.ModelEnum;
import com.projectmanage.common.ParamUtils;
import com.projectmanage.common.PriorityLevel;
import com.projectmanage.common.ProcessState;
import com.projectmanage.common.ProcessType;
import com.projectmanage.dao.process.ProcessModelDao;
import com.projectmanage.pojo.ProcessModel;
import com.projectmanage.pojo.ProcessModelExample;
import com.projectmanage.pojo.ProcessModelExample.Criteria;

@Controller
@RequestMapping("/processesmanage.web")
public class ProcessesManageCtrl  extends BaseCtrl{

	@Autowired
	private ProcessModelDao processModelDao;
	
	@RequestMapping()
	public String openLader(Model model, HttpServletRequest request, HttpSession  session) {
		System.out.println("========ProcessesManageCtrl:openLader(=============");
		
		model.addAttribute("processStates", ProcessState.undoneToJsonStr());
		model.addAttribute("processTypes", ProcessType.toJsonStr());
		model.addAttribute("itemAreaTypes", ItemAreaType.toJsonStr());
		model.addAttribute("priorityLevels", PriorityLevel.toJsonStr());
		model.addAttribute("itemsetEnables", ItemSetEnable.toJsonStr());
		model.addAttribute("itemsetSysTypes", ItemSetSysType.toJsonStr());
		model.addAttribute("itemsetTypes", ItemSetType.toJsonStr());
		model.addAttribute("itemsetUnits", ItemSetUnit.toJsonStr());
		//add by lianhr begin 2019/02/19
		model.addAttribute("itemmodels", ModelEnum.toJsonStr());
		//add by lianhr end
		return "processesmanage";
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params= "atn=pages")
	public ModelAndView pages(Model model, HttpServletRequest request, HttpSession session) {
		ModelAndView json = new ModelAndView( new MappingJackson2JsonView());
System.out.println("========ProcessesManageCtrl:pages(=============");	
	
		try {
			
			//获取数据库查询配置
			//获取查询个数配置 默认值10
			Integer limit = ParamUtils.getIntParameter(request, "limit", 10);
			//查询查询起始位置 默认值0
			Integer offset = ParamUtils.getIntParameter(request, "offset",0);
			//查询过滤方式
			String filter = ParamUtils.getParameter(request, "filter","");
			
			Map<String,Object> filterPara = null;
			ProcessModelExample example = new ProcessModelExample();
			Criteria criteria = example.or();
			criteria.andStateNotEqualTo(ProcessState.COMPLETE.getValue() );
			
			if( filter.length() > 0) {
				
			}
			
			if( limit.compareTo(0) > 0)
				example.setLimit(limit);
			if( offset.compareTo(0) > 0)
				example.setOffset(offset);

			//根据那个字段排序
			example.setOrderByClause("priority desc, id");
			//查询数据库 查询结果放到list
			List<ProcessModel> rows  = processModelDao.selectByExample(example) ;
			int count = processModelDao.countByExample(example);
			
			json.addObject("rows", rows);
			json.addObject("total", count);
			json.addObject("result", 1);
			
		}catch(Exception e) {
			
		}

		return json;
	}
}
