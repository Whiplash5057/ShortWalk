package in.devdesk.shortwalk.presenter;

import in.devdesk.shortwalk.Utilities.FinalStrings;
import in.devdesk.shortwalk.model.CallBack.ItemService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by richardandrews on 07/07/17.
 */

public class RestManager {
    private ItemService mItemService;


    public ItemService getmItemService()
    {
        if(mItemService == null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(FinalStrings.HTTP.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            mItemService = retrofit.create(ItemService.class);
        }

        return mItemService;
    }
}
