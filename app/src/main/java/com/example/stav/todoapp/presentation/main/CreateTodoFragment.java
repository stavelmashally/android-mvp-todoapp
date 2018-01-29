package com.example.stav.todoapp.presentation.main;

import android.app.ProgressDialog;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.stav.todoapp.App;
import com.example.stav.todoapp.R;
import com.example.stav.todoapp.di.component.DaggerActivityComponent;
import com.example.stav.todoapp.di.module.ActivityModule;

import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CreateTodoFragment extends DialogFragment implements CreateTodoContract.View {

    @BindView(R.id.til_text)
    TextInputLayout tilText;

    private ProgressDialog mProgressDialog;

    @Inject
    CreateTodoPresenter mPresenter;

    public CreateTodoFragment() {
        // required empty constructor
    }

    public static CreateTodoFragment newInstance(){
        return new CreateTodoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.DialogStyle);
        DaggerActivityComponent.builder()
                .appComponent(App.get(getActivity()).component())
                .activityModule(new ActivityModule(getActivity()))
                .build()
                .inject(this);
        mProgressDialog = new ProgressDialog(getActivity());
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setMessage(getResources().getString(R.string.saving_text));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_todo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onResume() {
        mPresenter.attachView(this);
        super.onResume();
    }

    @Override
    public void onDestroy() {
        mPresenter.detachView();
        super.onDestroy();
    }

    @OnClick(R.id.btn_save)
    public void saveClick(){
        String text = tilText.getEditText().getText().toString();
        mPresenter.saveTodo(text);
    }

    @Override
    public void showLoading() {
        mProgressDialog.show();
    }

    @Override
    public void hideLoading() {
        mProgressDialog.dismiss();
    }

    @Override
    public void setErrorTextField() {
        tilText.setError(getActivity().getString(R.string.text_error));
    }

    @Override
    public void saveTodoFailure() {
        Toast.makeText(getActivity(), getActivity().getString(R.string.some_error),
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void navigateToMainScreen() {
        if (getActivity() instanceof MainActivity){
            ((MainActivity)getActivity()).load();
            dismiss();
        }
    }
}
