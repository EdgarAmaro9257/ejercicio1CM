package com.example.ejercicio1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.example.ejercicio1.databinding.ActivityMain2Binding
import java.util.*

class MainActivity2 : AppCompatActivity() {

    private lateinit var binding:ActivityMain2Binding
    //private var carreraSeleccionada = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // se reciben los parametros
        val bundle = intent.extras
        // se guardan
        val name = bundle?.getString("name","")

        val date = bundle?.getString("date","")
        if (!date.isNullOrEmpty()) {
            zodiacCh(date)
        } else {
            // manejar la cadena de fecha vacía o nula aquí
        }

        val account = bundle?.getString("account","")
        val email = bundle?.getString("email","")
        val carrera = bundle?.getString("carrera","")

        with(binding){

            nombredelaPersona.text = name
            ageCalculator(date.toString())
            numerodeCuenta.text = account
            correoElectronico.text = email
            zodiacCh(date.toString())
            zodiac(date.toString())
            //obtenerImagenCarrera(String())

        }

        /*
        //Recibiendo imagenes de las carrera del spinner de la primera activity
        val imageView = binding.imagenCarreras
        imageView.setImageResource(obtenerImagenCarrera(carreraSeleccionada))
        */

        val imageView = findViewById<ImageView>(R.id.imagenCarreras)

        when (carrera) {
            "Ingeniería Aeroespacial" -> imageView.setImageResource(R.drawable.ingaero4)
            "Ingeniería Ambiental" -> imageView.setImageResource(R.drawable.ingambien)
            "Ingeniería en Civil" -> imageView.setImageResource(R.drawable.ingcivil)
            "Ingeniería en Computación" -> imageView.setImageResource(R.drawable.ingcompu)
            "Ingeniería Eléctrica Electrónica" -> imageView.setImageResource(R.drawable.ingelectro2)
            "Ingeniería Geofísica" -> imageView.setImageResource(R.drawable.ingeofisica)
            "Ingeniería Geológica" -> imageView.setImageResource(R.drawable.inggeologica)
            "Ingeniería Geomática" -> imageView.setImageResource(R.drawable.inggeoma2)
            "Ingeniería Mecánica" -> imageView.setImageResource(R.drawable.ingmecanica)
            "Ingeniería Mecantrónica" -> imageView.setImageResource(R.drawable.ingmecatronica)
            "Ingeniería de Minas y Metalurgia" -> imageView.setImageResource(R.drawable.ingminasmetal)
            "Ingeniería Petrolera" -> imageView.setImageResource(R.drawable.ingpetro)
            "Ingeniería en Sitemas Biomédicos" -> imageView.setImageResource(R.drawable.ingsisbio)
            "Ingeniería en Telecomunicaciones" -> imageView.setImageResource(R.drawable.ingtelecom)
        }


    }

    fun zodiacCh(date: String){
        if (date.isNotBlank()) {
            val tmp = splitDate(date)
            val year = tmp[2]

            when(year.toInt()){
                // RATA
                2020, 2008, 1996, 1984, 1972, 1960 -> {
                    binding.zodiacChino.setImageResource(R.drawable.rata2)
                }
                // BUEY
                2021, 2009, 1997, 1985, 1973, 1961 -> {
                    binding.zodiacChino.setImageResource(R.drawable.buey2)
                }
                // TIGRE
                2022, 2010, 1998, 1986, 1974, 1962 -> {
                    binding.zodiacChino.setImageResource(R.drawable.tigre)
                }
                // CONEJO
                2023, 2011, 1999, 1987, 1975, 1963 -> {
                    binding.zodiacChino.setImageResource(R.drawable.conejo)
                }
                // DRAGON
                2024, 2012, 2000, 1988, 1976, 1964 -> {
                    binding.zodiacChino.setImageResource(R.drawable.dragon)
                }
                // SERPIENTE
                2025, 2013, 2001, 1989, 1977, 1965 -> {
                    binding.zodiacChino.setImageResource(R.drawable.serpiente)
                }
                // CABALLO
                2026, 2014, 2002, 1990, 1978, 1966 -> {
                    binding.zodiacChino.setImageResource(R.drawable.caballo)
                }
                // CABRA
                2027, 2015, 2003, 1991, 1979, 1967 -> {
                    binding.zodiacChino.setImageResource(R.drawable.cabra)
                }
                // MONO
                2028, 2016, 2004, 1992, 1980, 1968 -> {
                    binding.zodiacChino.setImageResource(R.drawable.mono)
                }
                // GALLO
                2029, 2017, 2005, 1993, 1981, 1969 -> {
                    binding.zodiacChino.setImageResource(R.drawable.gallo)
                }
                // PERRO
                2030, 2018, 2006, 1994, 1982, 1970 -> {
                    binding.zodiacChino.setImageResource(R.drawable.perro)
                }
                // CERDO
                2031, 2019, 2007, 1995, 1983, 1971 -> {
                    binding.zodiacChino.setImageResource(R.drawable.cerdo)
                }
            }
        }else{

        }

    }


    fun splitDate(date: String): Array<String> {
        val tmp = date.split("-").toTypedArray()
        return if (tmp.size >= 3) {
            arrayOf(tmp[0], tmp[1], tmp[2])
        } else {
            arrayOf("", "", "")
        }
        // DAY MONTH YEAR
    }


    @SuppressLint("SetTextI18n")
    fun ageCalculator(date: String){
        val tmp = splitDate(date)
        val month = tmp[1]
        val year = tmp[2]
        //Obtener el mes y año de la fecha actual
        val c = Calendar.getInstance()
        val monthCurrent = c.get(Calendar.MONTH)
        val yearCurrent = c.get(Calendar.YEAR)
        /*
        if (month.toInt() <= monthCurrent+1){
            binding.edad.text = (yearCurrent - year.toInt()).toString()
        } else {
            binding.edad.text = ((yearCurrent - year.toInt())-1).toString()
        }
        */
        if (month.isNotEmpty() && year.isNotEmpty()) {
            if (month.toInt() <= monthCurrent+1){
                binding.edad.text = (yearCurrent - year.toInt()).toString()
            } else {
                binding.edad.text = ((yearCurrent - year.toInt())-1).toString()
            }
        } else {
            // Si las cadenas están vacías, muestra un mensaje de error o asigna un valor por defecto
            binding.edad.text = "Edad no disponible"
        }

    }




    fun zodiac(date: String){
        val tmp = splitDate(date)
        if (tmp.size == 3) {
            val day = tmp[0].toIntOrNull()
            val month = tmp[1].toIntOrNull()

            if (day != null && month != null) {
                when(month){
                    // Enero
                    1 -> {
                        if (day < 20){
                            // Capricornio
                            binding.Zodiac.setImageResource(R.drawable.capricornio)
                        } else {
                            // Acuario
                            binding.Zodiac.setImageResource(R.drawable.acuario)
                        }
                    }
                    // Febrero
                    2 -> {
                        if (day < 19){
                            // Acuario
                            binding.Zodiac.setImageResource(R.drawable.acuario)
                        } else {
                            // Piscis
                            binding.Zodiac.setImageResource(R.drawable.piscis2)
                        }
                    }
                    // Marzo
                    3 -> {
                        if (day < 21){
                            // Piscis
                            binding.Zodiac.setImageResource(R.drawable.piscis2)
                        } else {
                            // Aries
                            binding.Zodiac.setImageResource(R.drawable.aries)
                        }
                    }
                    // Abril
                    4 -> {
                        if (day < 20){
                            // Aries
                            binding.Zodiac.setImageResource(R.drawable.aries)
                        } else {
                            //  Tauro
                            binding.Zodiac.setImageResource(R.drawable.tauro2)
                        }
                    }
                    // Mayo
                    5 -> {
                        if (day < 21){
                            //  Tauro
                            binding.Zodiac.setImageResource(R.drawable.tauro2)
                        } else {
                            // Geminis
                            binding.Zodiac.setImageResource(R.drawable.geminis)
                        }
                    }
                    // Junio
                    6 -> {
                        if (day < 21){
                            // Geminis
                            binding.Zodiac.setImageResource(R.drawable.geminis)
                        } else {
                            // Cancer
                            binding.Zodiac.setImageResource(R.drawable.cancer)
                        }
                    }
                    // Julio
                    7 -> {
                        if (day < 23){
                            // Cancer
                            binding.Zodiac.setImageResource(R.drawable.cancer)
                        } else {
                            // Leo
                            binding.Zodiac.setImageResource(R.drawable.leo)
                        }
                    }
                    // Agosto
                    8 -> {
                        if (day < 23){
                            // Leo
                            binding.Zodiac.setImageResource(R.drawable.leo)
                        } else {
                            // Virgo
                            binding.Zodiac.setImageResource(R.drawable.virgo)
                        }
                    }
                    // Septiembre
                    9 -> {
                        if (day < 23){
                            // Virgo
                            binding.Zodiac.setImageResource(R.drawable.virgo)
                        } else {
                            // Libra
                            binding.Zodiac.setImageResource(R.drawable.libra)
                        }
                    }
                    // Octubre
                    10 -> {
                        if (day < 23){
                            // Libra
                            binding.Zodiac.setImageResource(R.drawable.libra)
                        } else {
                            // Scorpio
                            binding.Zodiac.setImageResource(R.drawable.escorpio)
                        }
                    }
                    // Noviembre
                    11 -> {
                        if (day < 22){
                            // Scorpio
                            binding.Zodiac.setImageResource(R.drawable.escorpio)
                        } else {
                            // Sagitario
                            binding.Zodiac.setImageResource(R.drawable.sagitario)
                        }
                    }
                    // Diciembre
                    12 -> {
                        if (day < 22){
                            // Sagitario
                            binding.Zodiac.setImageResource(R.drawable.sagitario)
                        } else {
                            // Capricornio
                            binding.Zodiac.setImageResource(R.drawable.capricornio)
                        }
                    }
                }
            }else {
                // mostrar mensaje de error
            }
        }else {
            // mostrar mensaje de error
        }
    }

}