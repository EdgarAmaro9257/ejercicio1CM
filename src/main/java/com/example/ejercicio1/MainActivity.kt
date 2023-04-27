package com.example.ejercicio1

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.ejercicio1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        //Haciendo Spinner para escoger carreras de FI
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)
        //Accediendo a items de la lista
        val carreras = resources.getStringArray(R.array.Carreras)
        //Accediendo a spinner
        val spinner = findViewById<Spinner>(R.id.spinner)
        if (spinner != null) {
            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item, carreras
            )
            spinner.adapter = adapter

            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View, position: Int, id: Long
                ) {
                    Toast.makeText(
                        this@MainActivity,
                        getString(R.string.escogerCarrera) + " " +
                                "" + carreras[position], Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }

    private fun datePicker(){
        // Valores por defecto del DatePicker
        val year = 2000
        val month = 0
        val day = 1

        val datePickerDialog = DatePickerDialog(
            this,
            { view, year1, monthOfYear, dayOfMonth ->
                val dateChoice = (dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year1)
                binding.fechaNaci.setText(dateChoice)
                //temp = dateChoice
            }, year, month, day
        )
        datePickerDialog.show()
    }


    //Acción del botón ACEPTAR y traslado hacia MainActivity2
    fun onClick(view: View) {

        when (view.id) {
            R.id.button -> {
                if (isNotEmpty()) {
                    // se pasa a la otra activity

                    val parameters = Bundle()

                    parameters.putString("name", binding.nombredelaPersona.text.toString())
                    parameters.putString("name2", binding.apellidos.text.toString())
                    parameters.putString("date", binding.fechaNaci.text.toString())
                    parameters.putString("email", binding.correoElectronico.text.toString())
                    parameters.putString("account", binding.numerodeCuenta.text.toString())

                    val intent = Intent(this, MainActivity2::class.java)
                    intent.putExtras(parameters)
                    startActivity(intent)

                } else {
                    Toast.makeText(
                        this@MainActivity, getString(R.string.instrucciones),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            R.id.fechaNaci -> {
                datePicker()
            }
        }
    }

    private fun isNotEmpty(): Boolean {
        val check = mutableListOf<Boolean>() // Para comprobar que haya texto
        with(binding) {
            val idsTexts = listOf(
                nombredelaPersona.text,
                apellidos.text,
                fechaNaci.text,
                correoElectronico.text,
                numerodeCuenta.text
            )

            for (id_text in idsTexts) {
                if (id_text.isEmpty()) {
                    idError(idsTexts.indexOf(id_text))
                    check.add(false) // Agregar un valor falso si el campo de texto está vacío
                } else {
                    check.add(true)
                }
            }
            return check.all { it } // Devolver verdadero si todos los elementos en check son verdaderos
        }
    }


    /*
    private fun isNotEmpty(): Boolean {
        val check = mutableListOf<Boolean>() // Para comprobar que haya texto
        with(binding) {
            val idsTexts = listOf(
                nombredelaPersona.text,
                apellidos.text,
                fechaNaci.text,
                correoElectronico.text,
                numerodeCuenta.text
            )

            for (id_text in idsTexts) {
                if (id_text.isEmpty()) {
                    idError(idsTexts.indexOf(id_text))
                } else {
                    check.add(true)
                }
            }
            return check.size == 4
        }
    }
*/
    private fun idError(id: Int) {
        when (id) {
            0 -> {
                binding.nombredelaPersona.error = getString(R.string.nombre_error)
            }
            1 -> {
                binding.apellidos.error = getString(R.string.nombre_error)
            }
            2 -> {
                binding.fechaNaci.error = getString(R.string.fecha_error)
            }
            3 -> {
                binding.correoElectronico.error = getString(R.string.correo_error)
            }
            4 -> {
                binding.numerodeCuenta.error = getString(R.string.cuenta_error)
            }
        }
    }
}

