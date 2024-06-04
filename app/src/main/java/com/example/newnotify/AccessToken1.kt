package com.example.newnotify

import com.google.auth.oauth2.GoogleCredentials
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.nio.charset.StandardCharsets

object AccessToken1 {
        private const val FIREBASE_MESSAGING_SCOPE =
            "https://www.googleapis.com/auth/firebase.messaging"

    fun getAccessToken(): String? {
         try {
            val jsonString = """
                {
                  "type": "service_account",
                  "project_id": "chatapp-7ecbf",
                  "private_key_id": "4a398ff3e104e2ee7b6a8661b9984d570e49d061",
                  "private_key": "-----BEGIN PRIVATE KEY-----\nMIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCv5YV+gP4WSAkF\n+fsvRYbwhjLwmNI2VgYRCRtH8T7q4PEAonPMLUH90hwxc/mMruLoWZfNJo2jXL+C\n6B68obfHVdKvVhmKnzK4PMeqpVtoNsckNV9Jos7nwW0V+RbHydNG6bM2gxBWpP8u\n4f7Vto1DSI/B3jCFC+H2DHIQXT4MEDEel+OFnFCn6xQV5mWTUgHZjmz4cejI2tJJ\n97Ob0XUOLTuJaATIc4m9KaOhHcczI1I0Pqhckk0Yak4xnjJGziOD+H+bzU2V/vom\n2bWoVpkNUN3wQ5DLprsrLIww+9WnCRd4BDzT8PP+7t6U1BUW0xawnxw7wim12CbZ\nywLKccLVAgMBAAECggEAKojuRzEaQHka9X8F5uCBSRiqqNPLwaf7v5LcLgGAnXvX\ny7d9GRq4opB0kODir8p6xIUl2E5CYgbDqPxIsoTI1ENEt3Gyq3rj4PmRrwpdmE3m\nyjK94CimsBRuNKoPgDCmDrfkxoHbKZisvMGZxkRm1KZvXQpF4WaGNeG8k4h4wKGh\n6FwpjZW1q4P/lVCv1fUVl5LfA7r2+imEJ6H4Mf82nQb9tlqEZL01kQDKmtf7l40O\nZ/VkQtItcAu+crUskgBOIJm6Fcb4Z2gJc/lAWSC2ik5k5MySyoP9bO+lMXe25ncX\npRjvzPHpg6lg3d5qPVFSMsYiZsCYPsIFcG6CA4F1AQKBgQD3X/Bm26SF02trwNAY\nnGJ30hX4V3ErLZb8z1PrlgHpjySwKPKqSULlsBguZnp/IAQ1OROEbxj9g3IpqYwG\nGujYXTDResp6pIpgukwL4x3TO52kSL71eE2kAVdbIzlC4sXpXQPqrqJGT+FxK7cK\ny3c1/jAaNeUO/k2KGVqjGdsvfQKBgQC2B5HgAwSIhDipWFA9m8AvPHJGhGlfDupv\nf/fpTKMUquFYHcXyewdhROvLXXzMBg8fOelsretJFak1fEC0x0cd6Dx1Z/Ch3Oax\nhlrvpOXQXfYtlakvTlPsHZp85HUH4FZWGS7DgzpqIw1ar0uwjNHe0lj0oi5GiRyz\ndMbqtMvwOQKBgBDfVFBPrtsJ3ovM7OSg1f6CL8lufWOKin/XzEuHQb4ia0gwosyG\njq+H4T5Y8wX0sxTGPdSMl1HDQ2ggDlLkRvlq8cd37pOK4X1Td5hf90rPCCOKaqvn\ndrQ2+zRWJLOxL/qIw5mgK6VMEX5ckvGDiy/9lF5VSrhJFBTVQd9aBh8pAoGAOFmf\njvlgLeLAp6vvKyNMG1NbDPlwpTy41kuHM3HGCl/b4qtU2/u6DSKI3CvUtlQp2kDT\nQhGVBMrv1t7gKRaHrXp3POEwXOtFfy9Yd1dUzWxJCVOLTFmzISUI7iDLGeUY1Q52\nq4+0RacUOz6I796oGZKEdza9nJhVijhcW8wcVgECgYEAzRnkt5xpGrpF4bQaSeaU\nDddpfHdK10oRiBmy5JokRJtnxeb57QgXYyCPLNg7jeF3vCNHqKYC1yacEiJtoN/h\ns2uVg7QPiLiaggI16HUSx2KZHGeMSSp2PBv4PNTpIC5Lzoe3Q5F56YE0zlaJ7ZPm\nWHbKnZpMxq8FQKKAS/UcNLM=\n-----END PRIVATE KEY-----",
                  "client_email": "firebase-adminsdk-wnhtk@chatapp-7ecbf.iam.gserviceaccount.com",
                  "client_id": "104302022244565034113",
                  "auth_uri": "https://accounts.google.com/o/oauth2/auth",
                  "token_uri": "https://oauth2.googleapis.com/token",
                  "auth_provider_x509_cert_url": "https://www.googleapis.com/oauth2/v1/certs",
                  "client_x509_cert_url": "https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-wnhtk%40chatapp-7ecbf.iam.gserviceaccount.com",
                  "universe_domain": "googleapis.com"
                }
            """.trimIndent()
            val stream: InputStream =
                ByteArrayInputStream(jsonString.toByteArray(StandardCharsets.UTF_8))
            val googleCredentials =
                GoogleCredentials.fromStream(stream).createScoped(listOf(FIREBASE_MESSAGING_SCOPE))
            googleCredentials.refresh()
            return googleCredentials.accessToken.tokenValue
        } catch (e: Exception) {
            println(e.localizedMessage)
            return null
        }
    }
}
