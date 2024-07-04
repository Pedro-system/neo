package com.test.test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.google.android.material.navigation.NavigationView;
import com.test.test.data.model.Data;
import com.test.test.databinding.ActivityMainBinding;
import com.test.test.ui.login.LoginActivity;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.FileProvider;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import android.net.Uri;

import java.io.File;

public class MainActivity extends AppCompatActivity
{
	private  SharedPreferences sp;
	private AppBarConfiguration mAppBarConfiguration;
	private ActivityMainBinding binding;

	private File fileTempPhoto ;
	private Uri tempPhotoUri;
	private ActivityResultLauncher<Uri> takePhoto = registerForActivityResult(new ActivityResultContracts.TakePicture(),
			new ActivityResultCallback<Boolean>() {
				@Override
				public void onActivityResult(Boolean success)
				{
					if (success)
					{
						ImageView iv = binding.navView.getHeaderView(0).findViewById(R.id.imageView);
						Glide.with(getBaseContext()).load( tempPhotoUri )
						     .signature(new ObjectKey(System.currentTimeMillis()))
						     .into(iv);
					}
				}
			});

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		sp = getSharedPreferences("login_shared", MODE_PRIVATE);
		setupPhotoUri();

		binding = ActivityMainBinding.inflate(getLayoutInflater());
		setContentView(binding.getRoot());

		setSupportActionBar(binding.appBarMain.toolbar);
		DrawerLayout drawer = binding.drawerLayout;
		NavigationView navigationView = binding.navView;
		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
		mAppBarConfiguration = new AppBarConfiguration.Builder(
				R.id.nav_home
		)
				.setOpenableLayout(drawer)
				.build();
		NavController navController =
				Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
		NavigationUI.setupWithNavController(navigationView, navController);

		AppCompatTextView tv = binding.navView.getHeaderView(0).findViewById(R.id.employee_name);
		ImageView iv = binding.navView.getHeaderView(0).findViewById(R.id.imageView);
		tv.setText( sp.getString(Data.EMPLOYEE_NAME,"") );
	    String  photoUrl = sp.getString(Data.PROFILE_IMAGE,"");
		if(photoUrl.isEmpty())
		{
			Glide.with(getBaseContext())
			     .load(AppCompatResources.getDrawable(getBaseContext(), R.mipmap.ic_launcher))
			     .signature(new ObjectKey(System.currentTimeMillis()))
			     .into(iv);
		}
		else
		{
			Glide.with(getBaseContext())
				.load(photoUrl)
			     .signature(new ObjectKey(System.currentTimeMillis()))
			     .into(iv);
		}
		iv.setOnClickListener(v ->
				                      takePhoto.launch(tempPhotoUri)
		);
	}

	private void setupPhotoUri( )
	{
		File file = new File(getBaseContext().getFilesDir().toString() + "/images");
		file.mkdirs();
		fileTempPhoto = new File(file.getAbsolutePath() + "/temp_image.jpg");
		tempPhotoUri =
				FileProvider.getUriForFile(
						getBaseContext(),
						getBaseContext().getPackageName()+".FileProvider",
						fileTempPhoto
				);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(@NonNull MenuItem item)
	{
		boolean result = item.getItemId() == R.id.action_logout;
		if (result)
		{
			sp.edit().putBoolean("login",false).apply();
			startActivity(new Intent(this, LoginActivity.class) );
			finish();
		}
		else
		{
			result = super.onOptionsItemSelected(item);
		}
		return result;
	}

	@Override
	public boolean onSupportNavigateUp()
	{
		NavController navController =
				Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
		return NavigationUI.navigateUp(navController, mAppBarConfiguration)
		       || super.onSupportNavigateUp();
	}
}