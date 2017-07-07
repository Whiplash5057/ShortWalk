package in.devdesk.shortwalk.model;

/**
 * Created by richardandrews on 28/06/17.
 */

public class UserProfileModel implements ShortWalkModel.UserProfileModel {
    @Override
    public String logDateSuccessful() {
        return "The date is ";
    }
}
