package cn.ekgc.ams.controller;

import cn.ekgc.ams.controller.base.BaseController;
import cn.ekgc.ams.pojo.entity.User;
import cn.ekgc.ams.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <b>校验控制器</b>
 * @author Wang
 * @version 1.0.0
 * @since 1.0.0
 */
@Controller("validationController")
@RequestMapping("/validation")
public class ValidationController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * <b>校验手机号码是否已注册</b>
	 * @return
	 * @throws Exception
	 */
	@PostMapping(value = "/cellphone")
	@ResponseBody
	public boolean validateCellphone(User user)  throws Exception {
		return userService.getUserByUser(user);
	}

	/**
	 * <b>校验邮箱是否已注册</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/email")
	@ResponseBody
	public boolean validateEmail(User user)  throws Exception {
		return userService.getUserByUser(user);
	}


	/**
	 * <b>校验密码是否正确</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/password")
	@ResponseBody
	public boolean validatePassword(String password)  throws Exception {
		return userService.getUserByPassword(password);
	}


	/**
	 * <b>校验身份证号是否已被使用</b>
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/idCard")
	@ResponseBody
	public boolean validateIdCard(User user)  throws Exception {
		return userService.getUserByUser(user);
	}
}
