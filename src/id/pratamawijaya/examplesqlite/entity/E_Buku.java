/*
 * Pratama Nur Wijaya (c) 2013 
 * 
 * Project       : ExampleSQLite
 * Filename      : E_Buku.java
 * Creation Date : Jul 24, 2013 time : 1:05:00 PM
 *
 */

package id.pratamawijaya.examplesqlite.entity;

/*
 * Class Entity Buku
 */
public class E_Buku
{

	private int		id;
	private String	judul;
	private String	penerbit;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getJudul()
	{
		return judul;
	}

	public void setJudul(String judul)
	{
		this.judul = judul;
	}

	public String getPenerbit()
	{
		return penerbit;
	}

	public void setPenerbit(String penerbit)
	{
		this.penerbit = penerbit;
	}

	@Override
	public String toString()
	{
		return this.judul;
	}

}
