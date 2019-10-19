package com.youga.baking.ps.controller;

import net.sf.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/member")
public class PrivilegeController {

    @RequestMapping("/privilege")
    public String center(HttpServletRequest req, HttpServletResponse resp, ModelMap model) throws IOException {

        return "memprivilege";

    }


}
