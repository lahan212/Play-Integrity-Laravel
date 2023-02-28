    private fun generateNonce(): String {
        val length = 50
        var nonce = ""
        val allowed = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789"
        for (i in 0 until length) {
            nonce += allowed[floor(Math.random() * allowed.length).toInt()].toString()
        }
        return nonce
    }

    private fun setIntegrity(context: Context) {

        try {
            val nonce: String = generateNonce()
            // Create an instance of a manager.
            val integrityManager = IntegrityManagerFactory.create(context)

            // Request the integrity token by providing a nonce.
            val integrityTokenResponse: Task<IntegrityTokenResponse> =
                integrityManager.requestIntegrityToken(
                    IntegrityTokenRequest.builder()
                        .setNonce(nonce)
                        .build()
                )
            integrityTokenResponse.addOnSuccessListener { integrityTokenResponse1 ->
                val integrityToken: String = integrityTokenResponse1.token()
                val packageName = context.packageName
              
                //send token with retrofit
              
                //checkIntegrity(context, packageName, integrityToken)
                
            }.addOnFailureListener { e ->

            }
        }catch (e:Exception){
            e.printStackTrace()
        }

    }
