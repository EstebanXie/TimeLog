package tig.timelog.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;

@SuppressWarnings("serial")
public class ExceptionHandlerAction extends ActionSupport {
    private Exception exception;
    private static final Logger logger = Logger.getLogger(ExceptionHandlerAction.class);

    @Override()
    public String execute() {
        if (exception != null) {
            logger.error(exception.toString());
            
            StackTraceElement[] ste = exception.getStackTrace();

            for (StackTraceElement aSte : ste) {
                logger.error(aSte);
            }
            exception.printStackTrace();
        }
        return "exception";
    }

    /**
     * @param exception the exception to set
     */
    public void setException(Exception exception) {
        this.exception = exception;
    }
}
