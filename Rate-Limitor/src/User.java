
public class User {
    private final String userId;
    private final UserTier tier;

    public String getUserId() {
        return userId;
    }

    public UserTier getTier() {
        return tier;
    }

    public User(String userId, UserTier tier) {
        this.userId = userId;
        this.tier = tier;
    }
}