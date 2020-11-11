package com.example.ch13;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDB;
    EditText productQuantity, productName;
    TextView productID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        Button bttnAdd = (Button) findViewById ( R.id.bttnAdd );
        Button bttnFind = (Button) findViewById ( R.id.bttnFind );
        Button bttnDelete = (Button) findViewById ( R.id.bttnDelete );

        myDB = new DatabaseHelper ( this );

        productID = (EditText) findViewById ( R.id.ProductID );
        productQuantity = (EditText) findViewById ( R.id.productQuantity );
        productName = (EditText) findViewById ( R.id.productName );

        bttnAdd.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                if (!myDB.addData ( "First Item", "9" )) {
                    Toast.makeText ( MainActivity.this, "Insertion failed", Toast.LENGTH_SHORT ).show ();
                }
                myDB.addData ( "Second Item", "13" );
            }

        } );

        bttnFind.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Cursor cur = myDB.getListContents ();
                StringBuffer buffer = new StringBuffer ();
                while (cur.moveToNext ()) {
                    buffer.append ( "id: " + cur.getString ( 0 ) + "\n" );
                    buffer.append ( "Name: " + cur.getString ( 1 ) + "\n" );
                    buffer.append ( "Quantity: " + cur.getString ( 2 ) + "\n\n" );
                }
                AlertDialog.Builder builder = new AlertDialog.Builder ( MainActivity.this );
                builder.setCancelable ( true );
                builder.setTitle ( "Data" );
                builder.setMessage ( buffer.toString () );
                builder.show ();

                Toast toast = Toast.makeText ( getApplicationContext (),
                        "Sucessful View",
                        Toast.LENGTH_SHORT );

                toast.show ();
            }
        } );


        bttnDelete.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                String id = productID.getText ().toString ();
                myDB.DeleteData ( id );
                Toast toast = Toast.makeText ( getApplicationContext (),
                        "Sucessful Delete",
                        Toast.LENGTH_SHORT );

                toast.show ();
            }
        } );


    }

}