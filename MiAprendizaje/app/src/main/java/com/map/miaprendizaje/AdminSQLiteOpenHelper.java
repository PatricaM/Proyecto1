package com.map.miaprendizaje;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    //Version de la BD
    private static final int DATABASE_VERSION = 1;

    //Nombre de la BD
    private static final String DATABASE_NAME = "MiAprendizaje";

    //Nombre de la tabla usuario
    private static final String TABLE_USERS = "user";

    //Nombre de las columnas de la tabla usuarios
    private static final String KEY_ID = "id";
    private static final String KEY_NOM = "nombre";
    private static final String KEY_AGE = "edad";

    public AdminSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Creacion de la tabla
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USER_TABLE="CREATE TABLE "+TABLE_USERS+"("
                +KEY_ID+" INTEGER PRIMARY KEY,"
                +KEY_NOM+" TEXT, "+KEY_AGE+" TEXT)";
        db.execSQL(CREATE_USER_TABLE);
    }

    //Actualizar la BD
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //Borra tablas antiguas si existen
        db.execSQL("DROP TABLE IF EXISTS " +
                TABLE_USERS);
        //
    }
    //CRUD (Create, Read, Update, Delete)
    //create, agregar users
    public  void addUser(Usuario usuario){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NOM,usuario.getNombre());
        values.put(KEY_AGE,usuario.getEdad());

        db.insert(TABLE_USERS,null,values);
        db.close();
    }

    //Read, Obtener todos los usuarios
    public Usuario getUsers(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.query(TABLE_USERS, new String[]{KEY_ID,KEY_NOM,KEY_AGE},
                KEY_ID+"=?", new String[]{String.valueOf(id)},null,null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
        Usuario usuario=new Usuario(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));

        return usuario;
    }

    //Read all, Obtener todos los usuarios
    public List<Usuario> getAllUsers(){
        ArrayList<Usuario> allUsers = new ArrayList<Usuario>();
        //Consultar todos
        String selectQuery="SELECT * FROM "+TABLE_USERS;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectQuery,null);
        //Iterar sobre todas ;as filas y las agrega a la lista
        if (cursor.moveToFirst()){
            do {
                Usuario usuario=new Usuario();
                usuario.setId(Integer.parseInt(cursor.getString(0)));
                usuario.setNombre(cursor.getString(1));
                usuario.setEdad(cursor.getString(2));
                //agrega contactos a la lista
                allUsers.add(usuario);
            }while(cursor.moveToNext());
        }
        return  allUsers;
    }

    //Update, Actualiza un usuario
    public void updateUsers(int id, String nombre, String edad){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_NOM, nombre);
        values.put(KEY_AGE, edad);

        db.update(TABLE_USERS,values,KEY_ID+"=?",new String[]{String.valueOf(id)});
    }

    //Delete, Borrar un usuario
    public void deleteUsers(Usuario usuario){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_USERS,KEY_ID+"=?",new String[]{String.valueOf(usuario.getId())});
    }

    //Obtiene el conteo de usuarios
    public int getUsersCount() {
        String countQuery = "SELECT * FROM " + TABLE_USERS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        return cursor.getCount();
    }
}
