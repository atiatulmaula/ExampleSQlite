package id.pratamawijaya.examplesqlite;

import id.pratamawijaya.examplesqlite.entity.E_Buku;
import id.pratamawijaya.examplesqlite.util.DBAdapter;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddDataActivity extends Activity implements OnClickListener
{
	private EditText	judul, penerbit;
	private Button		btnSimpan, btnReset;

	private DBAdapter	db;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_data);

		db = DBAdapter.getInstance(this);

		judul = (EditText) findViewById(R.id.input_judul);
		penerbit = (EditText) findViewById(R.id.input_penerbit);
		btnSimpan = (Button) findViewById(R.id.btnSimpan);
		btnReset = (Button) findViewById(R.id.btnReset);

		btnSimpan.setOnClickListener(this);
		btnReset.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_data, menu);
		return true;
	}

	@Override
	public void onClick(View v)
	{

		switch (v.getId())
		{
			case R.id.btnSimpan:
				E_Buku buku = new E_Buku();
				buku.setJudul(judul.getText().toString());
				buku.setPenerbit(penerbit.getText().toString());

				if (db.insertData(buku))
				{
					Toast.makeText(this, "Data Berhasil ditambahkan", Toast.LENGTH_SHORT).show();
					startActivity(new Intent(this, MainActivity.class));
					finish();
				} else
				{
					Toast.makeText(this, "Data Gagal ditambahkan", Toast.LENGTH_SHORT).show();

				}

				break;

		}
	}

	@Override
	public void onBackPressed()
	{
		super.onBackPressed();
		startActivity(new Intent(this, MainActivity.class));
		finish();
	}

}
