package com.google.example.ads.intermediate;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * A more advanced {@link Activity} that embeds an AdView and implements its
 * listener.
 */
public class BannerSample extends Activity implements AdListener {
  /** The log tag. */
  private static final String LOG_TAG = "BannerSample";

  /** The view to show the ad. */
  private AdView adView;

  /** Called when the activity is first created. */
  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.main);

    // Create an ad.
    adView = new AdView(this, AdSize.BANNER, AD_UNIT_ID_GOES_HERE);

    // Set the AdListener.
    adView.setAdListener(this);

    // Add the AdView to the view hierarchy. The view will have no size
    // until the ad is loaded.
    LinearLayout layout = (LinearLayout) findViewById(R.id.linearLayout);
    layout.addView(adView);

    // Create an ad request. Check logcat output for the hashed device ID to
    // get test ads on a physical device.
    AdRequest adRequest = new AdRequest();
    adRequest.addTestDevice(AdRequest.TEST_EMULATOR);

    // Start loading the ad in the background.
    adView.loadAd(adRequest);
  }

  /** Called before the activity is destroyed. */
  @Override
  public void onDestroy() {
    if (adView != null) {
      // Destroy the AdView.
      adView.destroy();
    }

    super.onDestroy();
  }

  /** Called when an ad is clicked and about to return to the application. */
  @Override
  public void onDismissScreen(Ad ad) {
    Log.d(LOG_TAG, "onDismissScreen");
    Toast.makeText(this, "onDismissScreen", Toast.LENGTH_SHORT).show();
  }

  /** Called when an ad was not received. */
  @Override
  public void onFailedToReceiveAd(Ad ad, AdRequest.ErrorCode error) {
    String message = "onFailedToReceiveAd (" + error + ")";
    Log.d(LOG_TAG, message);
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
  }

  /**
   * Called when an ad is clicked and going to start a new Activity that will
   * leave the application (e.g. breaking out to the Browser or Maps
   * application).
   */
  @Override
  public void onLeaveApplication(Ad ad) {
    Log.d(LOG_TAG, "onLeaveApplication");
    Toast.makeText(this, "onLeaveApplication", Toast.LENGTH_SHORT).show();
  }

  /**
   * Called when an Activity is created in front of the app (e.g. an
   * interstitial is shown, or an ad is clicked and launches a new Activity).
   */
  @Override
  public void onPresentScreen(Ad ad) {
    Log.d(LOG_TAG, "onPresentScreen");
    Toast.makeText(this, "onPresentScreen", Toast.LENGTH_SHORT).show();
  }

  /** Called when an ad is received. */
  @Override
  public void onReceiveAd(Ad ad) {
    Log.d(LOG_TAG, "onReceiveAd");
    Toast.makeText(this, "onReceiveAd", Toast.LENGTH_SHORT).show();
  }
}
