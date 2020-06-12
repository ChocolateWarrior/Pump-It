package com.pumpit.app.ui.view.activity.qr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.pumpit.app.R;
import com.pumpit.app.data.local.PumpItDatabase;
import com.pumpit.app.data.remote.PumpItApi;
import com.pumpit.app.data.remote.interceptor.InternetConnectionInterceptor;
import com.pumpit.app.data.repository.UserRepository;
import com.pumpit.app.databinding.ActivityQrGeneratorBinding;
import com.pumpit.app.databinding.ActivityQrScannerBinding;
import com.pumpit.app.ui.factory.QrGeneratorViewModelFactory;
import com.pumpit.app.ui.factory.QrScannerViewModelFactory;
import com.pumpit.app.ui.listener.qr.QrScannerListener;
import com.pumpit.app.ui.viewmodel.qr.QrGeneratorViewModel;
import com.pumpit.app.ui.viewmodel.qr.QrScannerViewModel;

import in.mrasif.libs.easyqr.EasyQR;
import in.mrasif.libs.easyqr.QRScanner;

public class QrScannerActivity extends AppCompatActivity implements View.OnClickListener, QrScannerListener {

    TextView hidden_result;
    Button btnQRScan;
    QrScannerViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final InternetConnectionInterceptor interceptor = new InternetConnectionInterceptor(this);
        final PumpItApi api = PumpItApi.invoke(interceptor);
        final PumpItDatabase db = PumpItDatabase.getInstance(this);
        final UserRepository repository = new UserRepository(api, db);
        final QrScannerViewModelFactory factory = new QrScannerViewModelFactory(repository);

        final ActivityQrScannerBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_qr_scanner);
        final QrScannerViewModel viewModel = new ViewModelProvider(this, factory).get(QrScannerViewModel.class);

//        setContentView(R.layout.activity_qr_scanner);
        hidden_result=findViewById(R.id.hidden_result);
        btnQRScan=findViewById(R.id.btnQRScan);

        binding.setViewmodel(viewModel);
        binding.setLifecycleOwner(this);
        viewModel.setListener(this);

        this.viewModel = viewModel;

        btnQRScan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnQRScan: {
                Intent intent=new Intent(QrScannerActivity.this, QRScanner.class);
                startActivityForResult(intent, EasyQR.QR_SCANNER_REQUEST);
            } break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case EasyQR.QR_SCANNER_REQUEST: {
                if (resultCode==RESULT_OK){
                    hidden_result.setText(data.getStringExtra(EasyQR.DATA));
                    viewModel.onResult(data.getStringExtra(EasyQR.DATA));
                }
            } break;
        }
    }
}