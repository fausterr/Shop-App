package app.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import app.model.DiscountCode;
import app.service.DiscountCodeService;

@Controller
public class Testowa {

//	private DiscountCodeService discountCodeService;
//	
//	public Code(DiscountCodeService discountCodeService) {
//		this.discountCodeService = discountCodeService;
//	}
//	
//	@GetMapping("/code")
//	public String aaa(Model model) {
//		model.addAttribute("discountCode", new DiscountCode());
//		return "code";
//	}
//	
//	@PostMapping("/checkCode")
//	public String bbb(@ModelAttribute @Valid DiscountCode discountCode, BindingResult bindResult) {
//		//discountCodeService.checkCode(discountCode.getValue());
//		if(bindResult.hasErrors()) {
//			return "code";
//		} else {
//			return "code2";
//		}
//	}
}
