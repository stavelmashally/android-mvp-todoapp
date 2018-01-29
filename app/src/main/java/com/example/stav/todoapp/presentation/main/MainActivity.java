package com.example.stav.todoapp.presentation.main;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.stav.todoapp.R;
import com.example.stav.todoapp.data.model.Todo;
import com.example.stav.todoapp.presentation.adapters.SpacingItemDecoration;
import com.example.stav.todoapp.presentation.adapters.TodoAdapter;
import com.example.stav.todoapp.presentation.authentication.AuthenticationActivity;
import com.example.stav.todoapp.presentation.base.BaseActivity;
import com.example.stav.todoapp.utils.Constants;

import java.util.List;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements MainContract.View {

    @BindView(R.id.empty_text)
    TextView mEmptyText;

    @BindView(R.id.progress_main)
    ProgressBar mProgress;

    @BindView(R.id.rv_todos)
    RecyclerView mRecyclerView;

    @Inject
    MainPresenter mPresenter;

    @Inject
    TodoAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject() {
        getActivityComponent().inject(this);
    }

    @Override
    protected void initUi() {
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.attachView(this);
        load();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    public void load(){
        mPresenter.getTodos();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.signout){
            mPresenter.logout();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void showLoading() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void showTodos(List<Todo> todoList) {
        mEmptyText.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAdapter.setTodoList(todoList);
    }

    @Override
    public void getTodosFailure(String error) {
        showSnackBar(error);
    }

    @Override
    public void showEmpty() {
        mEmptyText.setVisibility(View.VISIBLE);
    }

    @Override
    public void updateTodoSuccess() {

    }

    @Override
    public void updateTodoFailure(String message) {
        showSnackBar(message);
        load();
    }

    @Override
    public void removeTodoSuccess() {

    }

    @Override
    public void removeTodoFailure(String message) {
        showSnackBar(message);
        load();
    }

    @OnClick(R.id.fab_add)
    public void onFabAddClick(){
        DialogFragment dialogFragment = CreateTodoFragment.newInstance();
        dialogFragment.show(getSupportFragmentManager(), Constants.CREATE_TODO_FRAGMENT);
    }

    @Override
    public void navigateToAuthenticationScreen() {
        start(AuthenticationActivity.class);
        finish();
    }

    private void setupRecyclerView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.addItemDecoration(new SpacingItemDecoration(Constants.RECYCLER_SPACING));
        mRecyclerView.setAdapter(mAdapter);

        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.RIGHT | ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
                                  RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                if (direction == ItemTouchHelper.LEFT){ // Remove
                    mPresenter.removeTodo(mAdapter.get(position).getId());
                    mAdapter.deleteItem(position);
                    if (mAdapter.getItemCount() == 0){
                        showEmpty();
                    }
                } else { // Update
                    mPresenter.updateTodo(mAdapter.get(position).getId());
                    mAdapter.setItemCompleted(position);
                }
            }

            @Override
            public void onChildDraw(Canvas c,
                                    RecyclerView recyclerView,
                                    RecyclerView.ViewHolder viewHolder,
                                    float dX,
                                    float dY,
                                    int actionState,
                                    boolean isCurrentlyActive) {

                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
                    View itemView = viewHolder.itemView;
                    if (dX > 0) { // Right swipe
                        Paint paint = new Paint();
                        paint.setColor(getResources().getColor(R.color.colorGreen));
                        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX,
                                (float) itemView.getBottom(), paint);
                    } else { // Left swipe
                        Paint paint = new Paint();
                        paint.setColor(getResources().getColor(R.color.colorRed));
                        c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(),
                                (float) itemView.getRight(), (float) itemView.getBottom(), paint);
                    }
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState,
                            isCurrentlyActive);
                }
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(mRecyclerView);
    }
}
