package com.sku.CoronaMap.controller;

import com.sku.CoronaMap.domain.Board;
import com.sku.CoronaMap.domain.User;
import com.sku.CoronaMap.service.ApiService;
import com.sku.CoronaMap.service.BoardService;
import com.sku.CoronaMap.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/corona/*")
@RequiredArgsConstructor
public class CoronaController {

    private final ApiService apiService;
    private final BoardService boardService;
    private final UserService userService;
    private String board_message = "";

    @GetMapping("statistics")
    public ModelAndView statistics(HttpSession session, ModelAndView mav){
        /*
        StringBuffer result = new StringBuffer ();
        StringBuilder Date = new StringBuilder ();
        LocalDate now = LocalDate.now ();
        Date.append (now.getYear ()).append (now.getMonth ()).append (now.getDayOfMonth ());

        try{
            StringBuilder urlBuilder = new StringBuilder ("http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson");
            urlBuilder.append ("?" + URLEncoder.encode ("ServiceKey", "UTF-8") + "=AYVQTVFCUQ0Wda6v9brkZWrDRd2GBdfajmkkf0CLCdJVAuU2N0gz2S%2BmQvPNBlItSekbZy4ek%2BI3n7JZ3AIVYA%3D%3D");
            urlBuilder.append ("&" + URLEncoder.encode ("pageNo", "UTF-8") + "=" + URLEncoder.encode ("1", "UTF-8"));
            urlBuilder.append ("&" + URLEncoder.encode ("numOfRows", "UTF-8") + "=" + URLEncoder.encode ("10", "UTF-8"));
            urlBuilder.append ("&" + URLEncoder.encode ("startCreateDt", "UTF-8") + "=" + URLEncoder.encode ("20220203", "UTF-8"));
            urlBuilder.append ("&" + URLEncoder.encode ("endCreateDt", "UTF-8") + "=" + URLEncoder.encode ("20220203", "UTF-8"));
            urlBuilder.append ("type=html");
            URL url = new URL (urlBuilder.toString ());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection ();
            conn.setRequestMethod ("GET");
            BufferedReader rd;
            rd = new BufferedReader (new InputStreamReader (conn.getInputStream (), "UTF-8"));
            String line;
            while ((line = rd.readLine ()) != null){
                result.append (line+"\n");
            }
            rd.close ();
            conn.disconnect ();
        } catch (Exception e){
            e.printStackTrace ();
        }
        List<String> result;
        result = apiService.CallCoronaApi ("20220201", "20220201");
        */

        mav.setViewName("corona/statistics"); // 뷰의 이름
        mav.addObject("result",  apiService.CallCoronaApi ("20200410", "20200410"));
        mav.addObject("login_message", session.getAttribute ("name"));
        return mav;
    }

    @GetMapping("find")
    public ModelAndView find(HttpSession session, ModelAndView mav){
        mav.setViewName("corona/find"); // 뷰의 이름
        mav.addObject("login_message", session.getAttribute ("name"));
        return mav;
    }

    @GetMapping("board")
    public ModelAndView board(@PageableDefault Pageable pageable, HttpSession session, ModelAndView mav){
        mav.setViewName("corona/board"); // 뷰의 이름
        mav.addObject ("boardList", boardService.findBoardList(pageable));
        mav.addObject("login_message", session.getAttribute ("name"));
        mav.addObject("board_message", board_message);
        board_message = "";
        return mav;
    }

    @GetMapping({"form", "form/"})
    public ModelAndView form(@RequestParam(value = "id", defaultValue = "0") Long id, HttpSession session, ModelAndView mav){
        mav.setViewName("corona/form"); // 뷰의 이름
        mav.addObject ("board", boardService.findBoardById (id));
        mav.addObject("login_message", session.getAttribute ("name"));
        return mav;
    }

    // ajax
    @RequestMapping(value = "post", method = RequestMethod.POST)
    public ModelAndView postBoard(@ModelAttribute Board board, HttpSession session, ModelAndView mav) {
        User user = userService.findUser (session);
        if(user != null) {
            board.setUser (user);
            boardService.save (board);
            board_message = "save";
        }
        else {
            board_message = "fail";
        }
        mav.setViewName("redirect:board"); // 뷰의 이름
        return mav;
        //return new ResponseEntity<> ("{}", HttpStatus.CREATED);
    }

    @RequestMapping(value = "put", method = RequestMethod.POST)
    public ModelAndView putBoard(@ModelAttribute Board board, HttpSession session, ModelAndView mav) {
        User user = userService.findUser (session);
        if(user != null) {
            Board persistBoard = boardService.findBoardById (board.getId ());
            persistBoard.update (board);
            boardService.save (persistBoard); // save = insert + update
            board_message = "update";
        }
        else {
            board_message = "fail";
        }
        mav.setViewName("redirect:board");
        return mav;
        //return new ResponseEntity<> ("{}", HttpStatus.OK);
    }

    //@ResponseBody
    //@PostMapping
    //@PutMapping
    //@DeleteMapping
    //ResponseEntity<?>
    //@PathVariable = board/{id}/name/
    //@RequestParam = board+?id=&name=
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public ModelAndView deleteBoard(@ModelAttribute Board board, HttpSession session, ModelAndView mav) {
        User user = userService.findUser (session);
        if(user != null) {
            boardService.deleteById (board.getId ());
            board_message = "delete";
        }
        else {
            board_message = "fail";
        }
        mav.setViewName("redirect:board");
        return mav;
        //return new ResponseEntity<> ("{}", HttpStatus.OK);
    }
}
