package com.example.stav.todoapp.presentation.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.stav.todoapp.R;
import com.example.stav.todoapp.data.model.Todo;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by stav on 1/16/18.
 */

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoHolder> {

    private Context mContext;
    private List<Todo> mTodoList;

    public TodoAdapter(Context context) {
        mContext = context;
        mTodoList = Collections.emptyList();
    }

    @Override
    public TodoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.item_todo, parent, false);
        return new TodoHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoHolder holder, int position) {
        holder.bindTodo(mTodoList.get(position));
    }

    public void setTodoList(List<Todo> todoList) {
        mTodoList = todoList;
        notifyDataSetChanged();
    }

    public Todo get(int position) {
        return mTodoList.get(position);
    }

    public void deleteItem(int position){
        mTodoList.remove(position);
        notifyDataSetChanged();
    }

    public void setItemCompleted(int position){
        mTodoList.get(position).setCompleted();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mTodoList.isEmpty() ? 0 : mTodoList.size();
    }

    class TodoHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.state_text)
        TextView mStateText;

        @BindView(R.id.todo_text)
        TextView mTodoText;

        @BindView(R.id.date_text)
        TextView mDateText;

        TodoHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        void bindTodo(Todo todo) {
            mTodoText.setText(todo.getText());
            mDateText.setText(todo.getCreateDate());
            if (todo.isCompleted()) {
                setCompleted();
            } else {
                setActive();
            }
        }

        private void setCompleted(){
            mStateText.setCompoundDrawablesWithIntrinsicBounds(
                    mContext.getDrawable(R.drawable.ic_done), null, null, null);
            mStateText.setText(R.string.todo_completed);
        }

        private void setActive(){
            mStateText.setCompoundDrawablesWithIntrinsicBounds(
                    mContext.getDrawable(R.drawable.ic_active), null, null, null);
            mStateText.setText(R.string.todo_active);
        }
    }
}
