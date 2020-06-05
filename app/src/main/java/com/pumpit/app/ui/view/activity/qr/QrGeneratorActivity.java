package com.pumpit.app.ui.view.activity.qr;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.WriterException;
import com.pumpit.app.R;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.interceptor.InternetConnectionInterceptor;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.databinding.ActivityQrGeneratorBinding;
import com.pumpit.app.databinding.ActivityUpdateProfileBinding;
import com.pumpit.app.ui.factory.QrGeneratorViewModelFactory;
import com.pumpit.app.ui.factory.UpdateProfileViewModelFactory;
import com.pumpit.app.ui.listener.qr.QrGeneratorListener;
import com.pumpit.app.ui.viewmodel.qr.QrGeneratorViewModel;
import com.pumpit.app.ui.viewmodel.update.UpdateProfileViewModel;
import com.pumpit.app.util.ViewUtils;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QrGeneratorActivity extends AppCompatActivity implements QrGeneratorListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final InternetConnectionInterceptor interceptor = new InternetConnectionInterceptor(this);
        final PumpItApi api = PumpItApi.invoke(interceptor);
        final PumpItDatabase db = PumpItDatabase.getInstance(this);
        final UserRepository repository = new UserRepository(api, db);
        final QrGeneratorViewModelFactory factory = new QrGeneratorViewModelFactory(repository);

        final ActivityQrGeneratorBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_qr_generator);
        final QrGeneratorViewModel viewModel = new ViewModelProvider(this, factory).get(QrGeneratorViewModel.class);
        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.setListener(this);
        viewModel.generate();
    }

    @Override
    public void showQrForId(long id) {
        try {
            ((ImageView)findViewById(R.id.qr_code_holder)).setImageBitmap(new QRGEncoder(String.valueOf(id), null, QRGContents.Type.TEXT, 300).encodeAsBitmap());
        }catch (WriterException e) {
            ViewUtils.showToast(this, "Ops, something went wrong...");
        }

    }
}