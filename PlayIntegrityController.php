<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use Google\Client;
use Google\Service\PlayIntegrity;
use Google\Service\PlayIntegrity\DecodeIntegrityTokenRequest;

class PlayIntegrityController extends Controller
{

    public function playIntegrity(Request $request)
    {
        $auth = Auth::user();
        $json = storage_path("g-service/lahane-project-97211-9bd92f20caf5.json");
        $client = new Client();
        $client->setAuthConfig($json);
        $client->addScope(PlayIntegrity::PLAYINTEGRITY);
        $service = new PlayIntegrity($client);
        $tokenRequest = new DecodeIntegrityTokenRequest();
        $tokenRequest->setIntegrityToken($request->token);
        $result = $service->v1->decodeIntegrityToken($request->packagename, $tokenRequest);

        $response['error'] = false;
        $response['message'] = 'success';
        $response['data'] =  $result;
        return response($response, 200);
    }
}
