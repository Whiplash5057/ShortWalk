package in.devdesk.shortwalk.presenter;

import in.devdesk.shortwalk.model.UserProfileModel;

/**
 * Created by richardandrews on 28/06/17.
 */

public class UserProfilePresenter implements ShortWalkViewPresenter.UserProfilePresenter{
    @Override
    public String logDateSuccessful() {
        UserProfileModel uPMInstance = new UserProfileModel();
        String logDataValue = uPMInstance.logDateSuccessful();
        return logDataValue;
    }
}
