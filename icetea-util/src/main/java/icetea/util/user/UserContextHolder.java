package icetea.util.user;

public class UserContextHolder {

    private static final ThreadLocal<UserContext> userContextThreadLocal = new ThreadLocal<>();

    public static final UserContext getContext() {
        UserContext context = userContextThreadLocal.get();
        if (context == null) {
            context = new UserContext();
            userContextThreadLocal.set(context);
        }

        return userContextThreadLocal.get();
    }

    public static final void setUserContext(UserContext userContext) {
        userContextThreadLocal.set(userContext);
    }

}
