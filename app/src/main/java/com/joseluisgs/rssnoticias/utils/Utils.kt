package com.joseluisgs.rssnoticias.utils

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity


object Utils {
	/**
	 * Manda un correo
	 * @param activity [ERROR : FragmentActivity]
	 */
	fun mandarEMail(
		activity: FragmentActivity?,
		para: String = "",
		cc: String = "",
		asunto: String = "",
		texto: String = ""
	) {
		val intent = Intent(Intent.ACTION_SEND)
		intent.data = Uri.parse("mailto:")
		intent.type = "text/plain"
		// Los receptores deben ser un array, ya sean uno o varios, por eso los casteamos
		intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(para))
		intent.putExtra(Intent.EXTRA_CC, cc);
		intent.putExtra(Intent.EXTRA_SUBJECT, asunto)
		intent.putExtra(Intent.EXTRA_TEXT, texto)
		try {
			activity?.startActivity(Intent.createChooser(intent, "Enviar usando..."))
		} catch (e: Exception) {
			Toast.makeText(activity, e.message, Toast.LENGTH_LONG).show()
		}


	}

	/**
	 * Abre una dirección en en navegador
	 * @param activity FragmentActivity?
	 * @param url String
	 */
	fun abrirURL(activity: FragmentActivity?, url: String) {
		val intent = Intent(
			Intent.ACTION_VIEW,
			Uri.parse(url)
		)
		activity?.startActivity(intent)
	}

	/**
	 * Comprueba si tenemos una conexión activa en internet
	 * @param fragment Fragment
	 * @return Boolean
	 */
	private fun isNetworkAvailable(fragment: Fragment): Boolean {
		val connectivityManager =
			fragment.context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
		val activeNetworkInfo = connectivityManager.activeNetworkInfo
		return activeNetworkInfo != null && activeNetworkInfo.isConnected
	}


}