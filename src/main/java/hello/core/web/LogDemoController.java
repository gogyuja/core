package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
        private final LogDemoService logDemoService;
        //그냥 MyLogger를 사용하면 에러가 뜬다. MyLogger는 요청을 찾는데 쓰이지만 프로그램이 돌아갈때 주입하니까 요청이 없는데 주입하니까 에러가뜬다.
         //하지만 proxy를 사용하면 이렇게 사용해도 된다.
        private final MyLogger myLogger;

        //그래서 MyLogger 쌩으로 쓰는 대신 그때그때 필요한걸 찾아주는 ObjectProvider를 사용해라라
       //private final ObjectProvider<MyLogger> myLoggerProvider;

        @RequestMapping("log-demo")
        @ResponseBody
        public String logDemo(HttpServletRequest request){

            String requestURL = request.getRequestURI().toString();
            //objectProvider와 함께쓰기 위해
            //MyLogger myLogger = myLoggerProvider.getObject();

            myLogger.setRequestURL(requestURL);

            myLogger.log("controller test");
            logDemoService.logic("testId");
            return "OK";
        }
}
