public class MyActivity extends Activity {
	/* URL saved to be loaded after fb login */
	private static final String target_url="http://pocketmall.com.my";
	private static final String target_url_prefix="www.pocketmall.com.my";
	private Context mContext;
	private WebView mWebview;
	private WebView mWebviewPop;
	private FrameLayout mContainer;
	private long mLastBackPressTime = 0;
	private Toast mToast;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_urimalo);
		// final View controlsView =
		// findViewById(R.id.fullscreen_content_controls);
		CookieManager cookieManager = CookieManager.getInstance(); 
		cookieManager.setAcceptCookie(true); 
		mWebview = (WebView) findViewById(R.id.webview);
		//mWebviewPop = (WebView) findViewById(R.id.webviewPop);
		mContainer = (FrameLayout) findViewById(R.id.webview_frame);
		WebSettings webSettings = mWebview.getSettings();
		webSettings.setJavaScriptEnabled(true);
		webSettings.setAppCacheEnabled(true);
		webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
		webSettings.setSupportMultipleWindows(true);
		mWebview.setWebViewClient(new UriWebViewClient());
		mWebview.setWebChromeClient(new UriChromeClient());
		mWebview.loadUrl(target_url);
	
		mContext=this.getApplicationContext();
	
	}
	
	
	private class UriWebViewClient extends WebViewClient {
		@Override
		public boolean shouldOverrideUrlLoading(WebView view, String url) {
			String host = Uri.parse(url).getHost();
			//Log.d("shouldOverrideUrlLoading", url);
			if (host.equals(target_url_prefix)) 
			{
				// This is my web site, so do not override; let my WebView load
				// the page
				if(mWebviewPop!=null)
				{
					mWebviewPop.setVisibility(View.GONE);
					mContainer.removeView(mWebviewPop);
					mWebviewPop=null;
				}
				return false;
			}
	
			if(host.equals("m.facebook.com") || host.equals("www.facebook.com"))
			{
				return false;
			}
			// Otherwise, the link is not for a page on my site, so launch
			// another Activity that handles URLs
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
			startActivity(intent);
			return true;
		}
	
		@Override
		public void onReceivedSslError(WebView view, SslErrorHandler handler,
				SslError error) {
			Log.d("onReceivedSslError", "onReceivedSslError");
			//super.onReceivedSslError(view, handler, error);
		}
	}
	
	class UriChromeClient extends WebChromeClient {
	
		@Override
		public boolean onCreateWindow(WebView view, boolean isDialog,
				boolean isUserGesture, Message resultMsg) {
			mWebviewPop = new WebView(mContext);
			mWebviewPop.setVerticalScrollBarEnabled(false);
			mWebviewPop.setHorizontalScrollBarEnabled(false);
			mWebviewPop.setWebViewClient(new UriWebViewClient());
			mWebviewPop.getSettings().setJavaScriptEnabled(true);
			mWebviewPop.getSettings().setSavePassword(false);
			mWebviewPop.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
					ViewGroup.LayoutParams.MATCH_PARENT));
			mContainer.addView(mWebviewPop);
			WebView.WebViewTransport transport = (WebView.WebViewTransport) resultMsg.obj;
			transport.setWebView(mWebviewPop);
			resultMsg.sendToTarget();
	
			return true;
		}
	
		@Override
		public void onCloseWindow(WebView window) {
			Log.d("onCloseWindow", "called");
		}
	
	}
}