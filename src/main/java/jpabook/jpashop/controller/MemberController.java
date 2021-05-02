package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 등록 폼 (GET)
     * @param model
     * @return
     */
    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());     // MemberForm 클래스를 새로 생성하여 View에 넘겨준다.
        return "members/createMemberForm";
    }

    /**
     * 회원 등록 처리 (POST)
     * @param form
     * @param result
     * @return
     */
    @PostMapping("/members/new")
    public String create(@ModelAttribute("memberForm") @Valid MemberForm form, BindingResult result) {
        /*
        @Valid 애노테이션으로 MemberForm 클래스의 @NotEmpty 필드가 비어있으면 안된다.
        BindingResult 클래스에 오류를 담는다.
         */
        if (result.hasErrors()) {
            return "members/createMemberForm";
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/members";
    }

    /**
     * 회원 목록 조회
     * @param model
     * @return
     */
    @GetMapping(value = "/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
