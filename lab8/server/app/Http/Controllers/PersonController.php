<?php
/**
 * Created by PhpStorm.
 * User: claudiu
 * Date: 05.06.2017
 * Time: 18:35
 */

namespace App\Http\Controllers;


use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class PersonController extends Controller
{

    public function show($id = 1)
    {
        $person = DB::table('persons')->where('id', $id)->get()->first();
        if ($person) {
            return response()->json($person);
        }
    }

    public function update(Request $request)
    {
        $id = $request->input('id');
        $firstname = $request->input('firstname');
        $lastname = $request->input('lastname');
        $phone = $request->input('phone');
        $email = $request->input('email');
        DB::table('persons')
            ->where('id', $id)->update(
                [
                    'firstname' => $firstname,
                    'lastname' => $lastname,
                    'phone' => $phone,
                    'email' => $email
                ]
            );
    }
}