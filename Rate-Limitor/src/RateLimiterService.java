import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {

    private static RateLimiterService rateLimiterService;
    private final Map<UserTier, RateLimiter> rateLimiters = new HashMap<>();

    public static RateLimiterService getInstance(){
        if(rateLimiterService == null ){
            synchronized (RateLimiterService.class){
                if(rateLimiterService == null){
                    rateLimiterService = new RateLimiterService();
                }
            }
        }
        return rateLimiterService;
    }

    private RateLimiterService() {
        // Configure per-tier limits + algorithms
        rateLimiters.put(
                UserTier.FREE,
                RateLimiterFactory.createRateLimiter(
                        RateLimitType.TOKEN_BUCKET,
                        new RateLimitConfig(10, 60) // 10 req/min
                )
        );

        rateLimiters.put(
                UserTier.PREMIUM,
                RateLimiterFactory.createRateLimiter(
                        RateLimitType.FIXED_WINDOW,
                        new RateLimitConfig(100, 60) // 100 req/min
                )
        );
    }


    public boolean allowRequest(User user) {
        RateLimiter limiter = rateLimiters.get(user.getTier());
        if (limiter == null) {
            throw new IllegalArgumentException("No limiter configured for tier: " + user.getTier());
        }
        return limiter.allowRequest(user.getUserId());
    }
}