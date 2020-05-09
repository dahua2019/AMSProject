package cn.ekgc.ams.controller;

import ch.qos.logback.classic.net.SimpleSocketServer;
import cn.ekgc.ams.controller.base.BaseController;
import cn.ekgc.ams.controller.base.enums.StatusEnum;
import cn.ekgc.ams.pojo.entity.Role;
import cn.ekgc.ams.pojo.entity.User;
import cn.ekgc.ams.service.RoleService;
import cn.ekgc.ams.service.UserService;
import cn.ekgc.ams.util.MD5Util;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>批量处理控制器</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller("poiController")
@RequestMapping("/poi")
public class PoiController extends BaseController {

	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;

	/**
	 * <b>转发到批量处理页面</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/addExcel")
	public String forwardPoiPage() throws Exception {
		return "user/user_excel_add";
	}

	/**
	 * <b>通过excel添加用户</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/addUserByExcel")
	@ResponseBody
	public boolean addUserList(@RequestParam("uploadFile") MultipartFile uploadFile) throws Exception {
		if (uploadFile != null) {
			//创建一个 List 集合
			List<User> userList = new ArrayList<User>();
			//创建输入流
			InputStream in = uploadFile.getInputStream();
			//得到Excel工作簿对象 uploadFile 通过输入流读进程序
			Workbook wb = new XSSFWorkbook(in);
			//得到第一个 sheet Excel工作表对象
			Sheet sheet = wb.getSheetAt(0);
			//得到工作表的行数
			int rows = sheet.getPhysicalNumberOfRows();
			for (int i = 1; i < rows; i++) {
				//获取第 i 行的数据
				Row row = sheet.getRow(i);
				//封装 User 对象
				User user = new User();
				Role role = new Role();
				//获取该行的第一格
				user.setUsername(row.getCell(0).getStringCellValue());
				//获取该行的第二格
				user.setCellphone(row.getCell(1).getStringCellValue());
				//获取该行的第三格

				user.setGender(row.getCell(2).getStringCellValue());

				//获取该行的第四格
				role.setName(row.getCell(3).getStringCellValue());
				role = roleService.getRoleListByQuery(role).get(0);
				user.setRole(role);
				//获取该行的第五格
				user.setIdCard(row.getCell(4).getStringCellValue());
				//获取该行的第六格
				user.setEmail(row.getCell(5).getStringCellValue());
				user.setStatus(StatusEnum.STATUS_ENABLE.getCode());
				user.setPassword(MD5Util.encrypt("123456"));
				//将对象存入列表中
				userList.add(user);
			}
			//保存用户列表信息
			return userService.saveUserList(userList);
		}
		return false;
	}



	/**
	 * <b>下载用户信息</b>
	 * @return
	 * @throws Exception
	 */
	@GetMapping(value = "/download")
	@ResponseBody
	public void downLoadExcel() throws Exception {
		//获得用户列表信息
		List<User> userList = userService.getUserList();

		//创建 XSSFWorkBook 工作簿
		XSSFWorkbook wb = new XSSFWorkbook();
		//创建对应的工作表 sheet
		XSSFSheet sheet = wb.createSheet("用户信息Excel");
		XSSFRow	row =null;
		for (int i = 0; i < userList.size(); i++) {
			//创建对应的 row
			row = sheet.createRow(i);
			row.createCell(0).setCellValue(userList.get(i).getUsername());
			row.createCell(1).setCellValue(userList.get(i).getCellphone());
			row.createCell(2).setCellValue(userList.get(i).getGender());
			row.createCell(3).setCellValue((userList.get(i).getRole().getName()));
			row.createCell(4).setCellValue(userList.get(i).getIdCard());
			row.createCell(5).setCellValue(userList.get(i).getEmail());
		}
			Date dt = new Date();
			String fileName = new Long(dt.getTime()).toString();
			//进行 Excel 下载
			response.setContentType("application/octet-stream");
			response.setHeader("Content-disposition","attachment;filename="+ fileName + ".xlsx");

			OutputStream out = null;
			try {
				 out = response.getOutputStream();
				 wb.write(out);
				 out.close();
			} catch (Exception e) {
				 e.printStackTrace();
			}
	}
}
