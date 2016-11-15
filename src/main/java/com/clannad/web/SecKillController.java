package com.clannad.web;

import com.clannad.dto.Exposer;
import com.clannad.dto.SecKillExecution;
import com.clannad.dto.SecKillResult;
import com.clannad.entity.Seckill;
import com.clannad.enums.SecKillStateEnum;
import com.clannad.exception.RepeatKillException;
import com.clannad.exception.SecKillCloseException;
import com.clannad.service.SecKillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by F_ck on 2016/11/9.
 */
@Controller
@RequestMapping("/seckill")  //url:/模块/资源/{id}/细分
public class SecKillController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SecKillService secKillService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //list.jsp + model = ModelAndView
        List<Seckill> list = secKillService.getSecKillList();
        model.addAttribute("list", list);
        return "list";
    }

    @RequestMapping(value = "/{seckillId}/detail",method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") Long seckillId,Model model) {
        if(seckillId == null){
            return "redirct:/seckill/list";
        }
        Seckill seckill = secKillService.getById(seckillId);
        if(seckill == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    //ajax json
    @RequestMapping(value = "/{seckillId}/exposer",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecKillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId){
        SecKillResult<Exposer> result;
        try{
            Exposer exposer = secKillService.exportSecKillUrl(seckillId);
            result = new SecKillResult<Exposer>(true,exposer);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            result = new SecKillResult<Exposer>(false,e.getMessage());
        }
        return result;
    }

    /**
     * value = "killPhone" required = false
     * required = false标示value = "killPhone"不是必须的,
     * 否则如果cookie中不带killPhone字段会报错
     * 好处是可以将killPhone字段放到我们的逻辑中处理,
     * 而不是spring自己判断
     * @param seckillId
     * @param md5
     * @param phone
     * @return
     */
    @RequestMapping(value = "/{seckillId}/{md5}/execution",
                    method = RequestMethod.POST,
                    produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SecKillResult<SecKillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @CookieValue(value = "killPhone",required = false) Long phone){
        //springMVC valid
        if(phone == null){
            return new SecKillResult<SecKillExecution>(false,"未注册");
        }
        try{
            SecKillExecution secKillExecution = secKillService.executeSecKill(seckillId,phone,md5);
            return new SecKillResult<SecKillExecution>(true,secKillExecution);
        }catch (RepeatKillException e){
            SecKillExecution execution = new SecKillExecution(seckillId, SecKillStateEnum.REPEAT_KILL);
            return new SecKillResult<SecKillExecution>(false,execution);
        }catch (SecKillCloseException e){
            SecKillExecution execution = new SecKillExecution(seckillId, SecKillStateEnum.END);
            return new SecKillResult<SecKillExecution>(false,execution);
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            //TODO
            SecKillExecution execution = new SecKillExecution(seckillId, SecKillStateEnum.INNER_ERROR);
            return new SecKillResult<SecKillExecution>(false,execution);
        }
    }

    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public SecKillResult<Long> time(){
        Date now = new Date();
        return new SecKillResult<Long>(true,now.getTime());
    }

}
