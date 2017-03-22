package com.bugframework.auth.controller;

import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bugframework.common.utility.ResourceUtil;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

@Controller
@RequestMapping("/auth/captcha")
public class CaptchaController {
	@Autowired
	private Producer producer;

	@RequestMapping(value="",method=RequestMethod.GET)
	public void kaptcha(HttpServletRequest req, HttpServletResponse rsp)
			throws Exception {
		HttpSession session = ResourceUtil.getSession();
		rsp.setDateHeader("Expires", 0);
		rsp.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		rsp.addHeader("Cache-Control", "post-check=0, pre-check=0");
		rsp.setHeader("Pragma", "no-cache");
		rsp.setContentType("image/jpeg");

		String capText = producer.createText();
		session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

		BufferedImage image = producer.createImage(capText);
		ServletOutputStream out = rsp.getOutputStream();
		ImageIO.write(image, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
	}

}
