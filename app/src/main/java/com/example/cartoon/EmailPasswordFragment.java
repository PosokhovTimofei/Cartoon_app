package com.example.cartoon;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.cartoon.databinding.FragmentEmailPasswordBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class EmailPasswordFragment extends Fragment {

    private static final String TAG = "EmailPassword";


    private FragmentEmailPasswordBinding mBinding;

    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentEmailPasswordBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Кнопки
        mBinding.emailSignInButton.setOnClickListener(v -> {
            String email = mBinding.fieldEmail.getText().toString();
            String password = mBinding.fieldPassword.getText().toString();
            signIn(email, password);
        });
        mBinding.verifyEmailButton.setOnClickListener(v -> sendEmailVerification());
        mBinding.emailCreateAccountButton.setOnClickListener(v -> {
            String email = mBinding.fieldEmail.getText().toString();
            String password = mBinding.fieldPassword.getText().toString();
            createAccount(email, password);
        });
        mBinding.signOutButton.setOnClickListener(v -> signOut());
        mBinding.reloadButton.setOnClickListener(v -> reload());
        // Инициализация авторизации через FirebaseAuth
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Проверяем если пользователь авторизаован, то обновляем UI
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //reload();
            Navigation.findNavController(requireView()).navigate(R.id.action_emailPasswordFragment_to_cartoonListFragment);
        }
    }
    // Создание нового пользователя
    private void createAccount(String email, String password) {
        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Авторизация прошла успешно. Обновляем UI
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);
                    } else {
                        // Авторизация прошла с ошибкой. Показываем ее в Toast.
                        Toast.makeText(getContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }

                });
    }
    // Вход уже существующего пользователя
    private void signIn(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        if (!validateForm()) {
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), task -> {
                    if (task.isSuccessful()) {
                        // Авторизация прошла успешно. Обновляем UI
                        Log.d(TAG, "signInWithEmail:success");
                        FirebaseUser user = mAuth.getCurrentUser();
                        updateUI(user);


                        Navigation.findNavController(requireView()).navigate(R.id.action_emailPasswordFragment_to_cartoonListFragment);
                    } else {
                        // Авторизация прошла с ошибкой. Показываем ее в Toast.
                        Log.w(TAG, "signInWithEmail:failure", task.getException());
                        Toast.makeText(getContext(), "Authentication failed.",
                                Toast.LENGTH_SHORT).show();
                        updateUI(null);
                    }

                    if (!task.isSuccessful()) {
                        mBinding.status.setText(R.string.auth_failed);
                    }

                });
    }
    // Метод для отправки подтверждающего письма для входа. Необходима доп.настройка в Firebase.
    private void sendEmailVerification() {
        mBinding.verifyEmailButton.setEnabled(false);
        final FirebaseUser user = mAuth.getCurrentUser();
        assert user != null;
        user.sendEmailVerification()
                .addOnCompleteListener(requireActivity(), task -> {
                    mBinding.verifyEmailButton.setEnabled(true);
                    if (task.isSuccessful()) {
                        Toast.makeText(getContext(),
                                "Verification email sent to " + user.getEmail(),
                                Toast.LENGTH_SHORT).show();
                        // Переключение на новый фрагмент
//                        showCartoonListFragment();
                    } else {
                        Log.e(TAG, "sendEmailVerification", task.getException());
                        Toast.makeText(getContext(),
                                "Failed to send verification email.",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
//    private void showCartoonListFragment() {
//        Fragment cartoonListFragment = new CartoonListFragment();
//        requireActivity().getSupportFragmentManager().beginTransaction()
//                .replace(R.id.activity_main, cartoonListFragment)
//
//
//                .addToBackStack(null)
//                .commit();
//    }


    private void signOut() {
        mAuth.signOut();
        updateUI(null);
    }
    private void reload() {
        mAuth.getCurrentUser().reload().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                updateUI(mAuth.getCurrentUser());
                Toast.makeText(getContext(),
                        "Reload successful!",
                        Toast.LENGTH_SHORT).show();
            } else {
                Log.e(TAG, "reload", task.getException());
                Toast.makeText(getContext(),
                        "Failed to reload user.",
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    // Метод проверки заполненности полей
    private boolean validateForm() {
        boolean valid = true;

        String email = mBinding.fieldEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mBinding.fieldEmail.setError("Required.");
            valid = false;
        } else {
            mBinding.fieldEmail.setError(null);
        }

        String password = mBinding.fieldPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mBinding.fieldPassword.setError("Required.");
            valid = false;
        } else {
            mBinding.fieldPassword.setError(null);
        }

        return valid;
    }
    // Метод обновление UI
    private void updateUI(FirebaseUser user) {

        if (user != null) {
            mBinding.status.setText(getString(R.string.emailpassword_status_fmt,
                    user.getEmail(), user.isEmailVerified()));
            mBinding.detail.setText(getString(R.string.firebase_status_fmt, user.getUid()));

            mBinding.emailPasswordButtons.setVisibility(View.GONE);
            mBinding.emailPasswordFields.setVisibility(View.GONE);
            mBinding.signedInButtons.setVisibility(View.VISIBLE);

            if (user.isEmailVerified()) {
                mBinding.verifyEmailButton.setVisibility(View.GONE);
            } else {
                mBinding.verifyEmailButton.setVisibility(View.VISIBLE);
            }
        } else {
            mBinding.status.setText(R.string.signed_out);
            mBinding.detail.setText(null);

            mBinding.emailPasswordButtons.setVisibility(View.VISIBLE);
            mBinding.emailPasswordFields.setVisibility(View.VISIBLE);
            mBinding.signedInButtons.setVisibility(View.GONE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding = null;
    }
}