package com.example.mac.myrunapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

public class FragmentManage extends Fragment {

TextView read;
Button buuuuu;
String message;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_manage, container, false);
        buuuuu = view.findViewById(R.id.buuuuu);
        read = view.findViewById(R.id.read_note);
        buuuuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                FragmentRun fragment2 = (FragmentRun) getFragmentManager()
                        .findFragmentByTag("fragment2");
                EditText editText = (EditText) fragment2.getView()
                        .findViewById(R.id.tv_step2);
                Toast.makeText(getActivity(),
                        "--two->>" + editText.getText().toString(), 1).show();
            }
        });



        return view;
    }


    private void write2file(String a){
        try {
            File file = new File("/sdcard/acc.txt");//write the resultinto/sdcard/acc.txt
            if (!file.exists()){
                file.createNewFile();}
// Open a random access file stream for reading and writing
            RandomAccessFile randomFile = new
                    RandomAccessFile("/sdcard/acc.txt", "rw");
// The length of the file (the number of bytes)
            long fileLength = randomFile.length();
// Move the file pointer to the end of the file
            randomFile.seek(fileLength);
            randomFile.writeBytes(a);
            randomFile.close();
        } catch (IOException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
