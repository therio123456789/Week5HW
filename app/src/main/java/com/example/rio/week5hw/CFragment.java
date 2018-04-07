package com.example.rio.week5hw;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.rio.week5hw.Interface.DialogClickListener;
import com.example.rio.week5hw.Interface.RecyclerViewClickListener;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmList;


public class CFragment extends Fragment implements DialogClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String TAG = CFragment.class.getSimpleName();
    private RecyclerView recyclerView;
    private Button btnAddTask;
    private DataRecyclerViewAdapter dataRecyclerViewAdapter;
    private ArrayList<Act> acts;
    private Realm realm;
    public CFragment() {
        // Required empty public constructor
    }

    public static CFragment newInstance() {
        CFragment fragment = new CFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_c, container, false);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
      public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view,savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        btnAddTask = view.findViewById(R.id.btnAdd);
        btnAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickObject(null);
            }
        });
        // Set Acts
        acts = new ArrayList<>();
        RecyclerViewClickListener recyclerViewClickListener = new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                try {
                    onClickObject(position);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onLongClick(View view, int position) {
                onLongClickObject(position);
            }
        };
        dataRecyclerViewAdapter = new DataRecyclerViewAdapter(getActivity(),acts,recyclerViewClickListener);
        recyclerView.setAdapter(dataRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
      }
    public void RecyclerViewUpdate() {
        dataRecyclerViewAdapter.notifyDataSetChanged();
    }
    @Override
    public void onClickObject(final Object object) {
        Act act = null;
        LayoutInflater layoutInflater = getLayoutInflater();
        View layout = layoutInflater.inflate(R.layout.layout_custom_dialog,null);
        final EditText txtName = layout.findViewById(R.id.txtName);
        final DatePicker datePicker = layout.findViewById(R.id.datePicker);
        final RadioGroup radioPriority = layout.findViewById(R.id.radioPriority);
        if(object != null) {
            int position =(int) object;
            act = acts.get(position);
            txtName.setText(act.getName());
            NDate date = act.getDeathLine();
            datePicker.updateDate(Integer.valueOf(date.getYear()),Integer.valueOf(date.getMothNumber())-1,Integer.valueOf(date.getDay()));
            for(int i = 0; i < radioPriority.getChildCount();i++) {
                RadioButton radioButton = ((RadioButton) radioPriority.getChildAt(i));
                if(String.valueOf(radioButton.getText()).equals(act.getPriority()))  {
                    radioButton.setChecked(true);
                }
            }
        }
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setView(layout);
        alert.setCancelable(false);
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        final Act finalAct = act;
        alert.setPositiveButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if(finalAct == null) {
                    RadioButton radioButton = radioPriority.findViewById(radioPriority.getCheckedRadioButtonId());
                    Act mAct = new Act(String.valueOf(txtName.getText()),String.valueOf(datePicker.getDayOfMonth()),String.format("%02d",datePicker.getMonth()+1),String.valueOf(datePicker.getYear()),String.valueOf(radioButton.getText()),"TODO");
                    acts.add(mAct);
                    RecyclerViewUpdate();
                } else {
                    int position =(int) object;
                    Act act = acts.get(position);
                    RadioButton radioButtonChecked = radioPriority.findViewById(radioPriority.getCheckedRadioButtonId());
                    act.setName(txtName.getText().toString());
                    act.setDeathline(String.valueOf(datePicker.getDayOfMonth()),String.format("%02d",datePicker.getMonth()+1),String.valueOf(datePicker.getYear()));
                    act.setPriority(String.valueOf(radioButtonChecked.getText()));
                    RecyclerViewUpdate();
                }
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

    @Override
    public void onLongClickObject(final Object object) {
        AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
        alert.setCancelable(false);
        alert.setTitle("Do you want to delete ?");
        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int pos = (int) object;
                acts.remove(pos);
                RecyclerViewUpdate();
                Log.d(TAG,"LongClick");
            }
        });
        AlertDialog dialog = alert.create();
        dialog.show();
    }

}