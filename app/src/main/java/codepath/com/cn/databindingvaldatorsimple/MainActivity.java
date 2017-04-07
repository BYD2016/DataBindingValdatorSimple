package codepath.com.cn.databindingvaldatorsimple;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Arrays;

import br.com.ilhasoft.support.validation.Validator;
import codepath.com.cn.databindingvaldatorsimple.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements Validator.ValidationListener {

    private static final String TAG = "MainActivity";

    private ActivityMainBinding binding;
    private Validator validator;

    private View.OnClickListener onValidateNameClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            validator.validate(binding.name);
        }
    };

    private View.OnClickListener onValidateMultipleClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            validator.validate(Arrays.asList(binding.username, binding.email));
        }
    };

    private View.OnClickListener onValidateAllClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (validator.validate()) {
                saveToDatabase();
            } else {
                Toast.makeText(MainActivity.this, "Dados inválidos!", Toast.LENGTH_SHORT).show();
            }
        }
    };

    private View.OnClickListener onValidateAllWithListenerClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            validator.toValidate();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.validateName.setOnClickListener(onValidateNameClickListener);
        binding.validateMultiple.setOnClickListener(onValidateMultipleClickListener);
        binding.validate.setOnClickListener(onValidateAllClickListener);
        binding.toValidate.setOnClickListener(onValidateAllWithListenerClickListener);

        validator = new Validator(binding);
        validator.setValidationListener(this);
        validator.enableFormValidationMode();

    }


    @Override
    public void onValidationSuccess() {
        saveToDatabase();
    }

    private void saveToDatabase() {
        Log.i(TAG, "Salvar os dados no banco de dados");
    }

    @Override
    public void onValidationError() {
        Toast.makeText(MainActivity.this, "Dados inválidos!", Toast.LENGTH_SHORT)
                .show();
    }


}
