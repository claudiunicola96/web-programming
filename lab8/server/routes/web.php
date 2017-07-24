<?php

/*
|--------------------------------------------------------------------------
| Application Routes
|--------------------------------------------------------------------------
|
| Here is where you can register all of the routes for an application.
| It is a breeze. Simply tell Lumen the URIs it should respond to
| and give it the Closure to call when that URI is requested.
|
*/

use Illuminate\Support\Facades\DB;

$app->get('/1', function () {
    return view('person');
});
$app->get('/person[/{id}]', 'PersonController@show');

$app->post('/person', 'PersonController@update');

$app->get('/upload', [
    'middleware' => 'cors',
    function () {
        return DB::table('files')->select('id', 'name', 'type', 'size')->get();
    }
]);

$app->get('/upload/{type}', [
    'middleware' => 'cors',
    function ($type) {
        if ($type == 'types') {
            return DB::table('files')->select('type')->distinct()->get();
        } else {
            $type = str_replace('-', '/', $type);
            return DB::table('files')->where('type', $type)->get();
        }
    }
]);

$app->post('/upload', [
    'middleware' => 'cors',
    function (\Illuminate\Http\Request $request) {

        if ($request->file('file')) {
            $file = $request->file('file');
            $path = base_path() . '/public/images';
            $file->move($path, $file->getClientOriginalName());

            $name = $a = substr_replace($file->getClientOriginalName(), '',
                strpos($file->getClientOriginalName(), '.'));
            DB::table('files')->insert(
                [
                    'name' => $name,
                    'type' => $file->getClientMimeType(),
                    'size' => $file->getClientSize(),
                    'path' => $path . $file->getClientOriginalName()
                ]);
        } else {
            return response()->json(['statusText' => 'Please choice a file.'], 400);
        }
    }
]);

$app->options('/action/{id}', [
    'middleware' => 'cors',
    function ($id) {
        //chrome send options request and after
        //the delete request. @todo: investigate
    }
]);

$app->delete('/action/{id}', [
    'middleware' => 'cors',
    function ($id) {
        DB::table('files')->where('id', $id)->delete();
    }
]);

$app->get('/action/{id}', [
    'middleware' => 'cors',
    function ($id) {
        return response()->json(DB::table('files')->where('id', $id)->get()->first());
    }
]);

$app->post('/action', [
    'middleware' => 'cors',
    function (\Illuminate\Http\Request $request) {
        $id = $request->input('id');
        $name = $request->input('name');
        DB::table('files')->where('id', $id)->update(['name' => $name]);
    }
]);

