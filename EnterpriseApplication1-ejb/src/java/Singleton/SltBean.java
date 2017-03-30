package Singleton;

import java.io.Serializable;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 *
 * @author Yakov
 */
@Singleton
@LocalBean
public class SltBean implements Serializable {
    private int count;
    
    public SltBean() {
        count = 0;
    }
    
    @AroundInvoke
    public Object numberOfCalls(InvocationContext ctx) throws Exception {
        count++;
        return ctx.proceed();
    }    
    
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
}
