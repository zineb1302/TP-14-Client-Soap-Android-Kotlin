package ma.projet.soapclient.ws

import ma.projet.soapclient.beans.Compte
import ma.projet.soapclient.beans.TypeCompte
import java.text.SimpleDateFormat
import java.util.*

// Version temporaire sans ksoap2 pour permettre la compilation
// TODO: Ajouter ksoap2-android-3.6.4.jar dans app/libs/ et restaurer les imports ksoap2

class Service {
    private val NAMESPACE = "http://ws.soapAcount/"
    private val URL = "http://10.0.2.2:8082/services/ws"
    private val METHOD_GET_COMPTES = "getComptes"
    private val METHOD_CREATE_COMPTE = "createCompte"
    private val METHOD_DELETE_COMPTE = "deleteCompte"
    
    /**
     * Récupère la liste des comptes via le service SOAP.
     * Version temporaire avec données de test
     */
    fun getComptes(): List<Compte> {
        // Données de test pour l'affichage
        return listOf(
            Compte(
                id = 1,
                solde = 2000.0,
                dateCreation = Date(),
                type = TypeCompte.COURANT
            ),
            Compte(
                id = 2,
                solde = 30007.0,
                dateCreation = Date(),
                type = TypeCompte.COURANT
            ),
            Compte(
                id = 3,
                solde = 45999.688,
                dateCreation = Date(),
                type = TypeCompte.EPARGNE
            )
        )
        
        /* Code SOAP original (nécessite ksoap2):
        val request = SoapObject(NAMESPACE, METHOD_GET_COMPTES)
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11).apply {
            dotNet = false
            setOutputSoapObject(request)
        }
        val transport = HttpTransportSE(URL)
        val comptes = mutableListOf<Compte>()
        
        try {
            transport.call("", envelope)
            val response = envelope.bodyIn as? SoapObject
            if (response != null) {
                for (i in 0 until response.propertyCount) {
                    val soapCompte = response.getProperty(i) as? SoapObject
                    if (soapCompte != null) {
                        val compte = Compte(
                            id = soapCompte.getPropertySafelyAsString("id")?.toLongOrNull(),
                            solde = soapCompte.getPropertySafelyAsString("solde")?.toDoubleOrNull() ?: 0.0,
                            dateCreation = try {
                                SimpleDateFormat("yyyy-MM-dd").parse(
                                    soapCompte.getPropertySafelyAsString("dateCreation") ?: ""
                                ) ?: Date()
                            } catch (e: Exception) {
                                Date()
                            },
                            type = try {
                                TypeCompte.valueOf(soapCompte.getPropertySafelyAsString("type") ?: "COURANT")
                            } catch (e: Exception) {
                                TypeCompte.COURANT
                            }
                        )
                        comptes.add(compte)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        
        return comptes
        */
    }
    
    /**
     * Crée un nouveau compte via le service SOAP.
     */
    fun createCompte(solde: Double, type: TypeCompte): Boolean {
        // Version temporaire - retourne toujours true
        return true
        
        /* Code SOAP original:
        val request = SoapObject(NAMESPACE, METHOD_CREATE_COMPTE).apply {
            addProperty("solde", solde)
            addProperty("type", type.name)
        }
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11).apply {
            dotNet = false
            setOutputSoapObject(request)
        }
        val transport = HttpTransportSE(URL)
        
        return try {
            transport.call("", envelope)
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        */
    }
    
    /**
     * Supprime un compte en fonction de son ID via le service SOAP.
     */
    fun deleteCompte(id: Long): Boolean {
        // Version temporaire - retourne toujours true
        return true
        
        /* Code SOAP original:
        val request = SoapObject(NAMESPACE, METHOD_DELETE_COMPTE).apply {
            addProperty("id", id)
        }
        val envelope = SoapSerializationEnvelope(SoapEnvelope.VER11).apply {
            dotNet = false
            setOutputSoapObject(request)
        }
        val transport = HttpTransportSE(URL)
        
        return try {
            transport.call("", envelope)
            val response = envelope.bodyIn
            if (response is Boolean) {
                response
            } else {
                true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
        */
    }
}
