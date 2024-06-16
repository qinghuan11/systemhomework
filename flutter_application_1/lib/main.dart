import 'package:flutter/material.dart';
import 'package:shared_preferences/shared_preferences.dart';
import 'dart:convert';

void main() => runApp(ColorPaletteApp());

class ColorPaletteApp extends StatefulWidget {
  @override
  _ColorPaletteAppState createState() => _ColorPaletteAppState();
}

class _ColorPaletteAppState extends State<ColorPaletteApp> {
  Color _primaryColor = Colors.blue;

  void _updatePrimaryColor(Color color) {
    setState(() {
      _primaryColor = color;
    });
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: '配色卡',
      theme: ThemeData(
        primarySwatch: generateMaterialColor(_primaryColor),
      ),
      home: LoginPage(onColorSelected: _updatePrimaryColor),
      routes: {
        '/register': (context) => RegisterPage(),
        '/home': (context) => HomePage(onColorSelected: _updatePrimaryColor),
      },
    );
  }

  MaterialColor generateMaterialColor(Color color) {
    return MaterialColor(
      color.value,
      <int, Color>{
        50: color.withOpacity(.1),
        100: color.withOpacity(.2),
        200: color.withOpacity(.3),
        300: color.withOpacity(.4),
        400: color.withOpacity(.5),
        500: color.withOpacity(.6),
        600: color.withOpacity(.7),
        700: color.withOpacity(.8),
        800: color.withOpacity(.9),
        900: color.withOpacity(1),
      },
    );
  }
}

class LoginPage extends StatefulWidget {
  final Function(Color) onColorSelected;

  LoginPage({required this.onColorSelected});

  @override
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  late String _username, _password;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          '配色卡APP登录',
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
        centerTitle: true,
      ),
      body: Container(
        padding: EdgeInsets.all(20.0),
        child: Form(
          key: _formKey,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              TextFormField(
                decoration: InputDecoration(
                  labelText: '用户名',
                  prefixIcon: Icon(Icons.person),
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return '请输入用户名';
                  }
                  return null;
                },
                onSaved: (value) {
                  _username = value!;
                },
              ),
              SizedBox(height: 20.0),
              TextFormField(
                decoration: InputDecoration(
                  labelText: '密码',
                  prefixIcon: Icon(Icons.lock),
                ),
                obscureText: true,
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return '请输入密码';
                  }
                  return null;
                },
                onSaved: (value) {
                  _password = value!;
                },
              ),
              SizedBox(height: 20.0),
              ElevatedButton(
                onPressed: () async {
                  if (_formKey.currentState!.validate()) {
                    _formKey.currentState!.save();
                    final prefs = await SharedPreferences.getInstance();
                    Map<String, dynamic> accounts =
                        jsonDecode(prefs.getString('accounts') ?? '{}');
                    if (accounts[_username] == _password) {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('登录成功！')),
                      );
                      Navigator.pushReplacementNamed(context, '/home');
                    } else {
                      ScaffoldMessenger.of(context).showSnackBar(
                        SnackBar(content: Text('无效的凭据！')),
                      );
                    }
                  }
                },
                child: Text('登录'),
                style: ElevatedButton.styleFrom(
                  padding: EdgeInsets.symmetric(vertical: 15.0),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(30.0),
                  ),
                ),
              ),
              SizedBox(height: 10.0),
              TextButton(
                onPressed: () {
                  Navigator.pushNamed(context, '/register');
                },
                child: Text(
                  '没有账号？注册',
                  style: TextStyle(color: Colors.blue),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}


class RegisterPage extends StatefulWidget {
  @override
  _RegisterPageState createState() => _RegisterPageState();
}

class _RegisterPageState extends State<RegisterPage> {
  final GlobalKey<FormState> _formKey = GlobalKey<FormState>();
  late String _username, _password;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(
          '注册',
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
        centerTitle: true,
      ),
      body: Container(
        padding: EdgeInsets.all(20.0),
        child: Form(
          key: _formKey,
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            mainAxisAlignment: MainAxisAlignment.center,
            children: <Widget>[
              TextFormField(
                decoration: InputDecoration(
                  labelText: '用户名',
                  prefixIcon: Icon(Icons.person),
                ),
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return '请输入用户名';
                  }
                  return null;
                },
                onSaved: (value) {
                  _username = value!;
                },
              ),
              SizedBox(height: 20.0),
              TextFormField(
                decoration: InputDecoration(
                  labelText: '密码',
                  prefixIcon: Icon(Icons.lock),
                ),
                obscureText: true,
                validator: (value) {
                  if (value == null || value.isEmpty) {
                    return '请输入密码';
                  }
                  return null;
                },
                onSaved: (value) {
                  _password = value!;
                },
              ),
              SizedBox(height: 20.0),
              ElevatedButton(
                onPressed: () async {
                  if (_formKey.currentState!.validate()) {
                    _formKey.currentState!.save();
                    final prefs = await SharedPreferences.getInstance();
                    Map<String, dynamic> accounts =
                        jsonDecode(prefs.getString('accounts') ?? '{}');
                    accounts[_username] = _password;
                    await prefs.setString('accounts', jsonEncode(accounts));
                    ScaffoldMessenger.of(context).showSnackBar(
                      SnackBar(content: Text('注册成功！')),
                    );
                    Navigator.pop(context);
                  }
                },
                child: Text('注册'),
                style: ElevatedButton.styleFrom(
                  padding: EdgeInsets.symmetric(vertical: 15.0),
                  shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(30.0),
                  ),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}


class HomePage extends StatelessWidget {
  final Function(Color) onColorSelected;

  HomePage({required this.onColorSelected});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: ColorListScreen(onColorSelected: onColorSelected),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // 点击按钮时跳转回登录界面
          Navigator.pushReplacementNamed(context, '/');
        },
        child: Icon(Icons.arrow_back),
      ),
    );
  }
}


class ColorListScreen extends StatefulWidget {
  final Function(Color) onColorSelected;

  ColorListScreen({required this.onColorSelected});

  @override
  _ColorListScreenState createState() => _ColorListScreenState();
}

class _ColorListScreenState extends State<ColorListScreen> {
  Color? _appBarColor;

  void _handleColorChange(Color color) {
    widget.onColorSelected(color);
    setState(() {
      _appBarColor = color;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('配色卡'),
        backgroundColor: _appBarColor ?? Theme.of(context).primaryColor,
      ),
      body: ColorList(onColorSelected: _handleColorChange),
    );
  }
}

class ColorList extends StatelessWidget {
  final Function(Color) onColorSelected;

  ColorList({required this.onColorSelected});

  final List<Map<String, String>> colors = [
    {'name': '红色/Red', 'hex': '#F44336'},
    {'name': '粉色/Pink', 'hex': '#E91E63'},
    {'name': '紫色/Purple', 'hex': '#9C27B0'},
    {'name': '深紫/Deep Purple', 'hex': '#673AB7'},
    {'name': '靛蓝/Indigo', 'hex': '#3F51B5'},
    {'name': '蓝色/Blue', 'hex': '#2196F3'},
    {'name': '亮蓝/Light Blue', 'hex': '#03A9F4'},
    {'name': '青色/Cyan', 'hex': '#00BCD4'},
    {'name': '鸭绿/Teal', 'hex': '#009688'},
    {'name': '绿色/Green', 'hex': '#4CAF50'},
    {'name': '亮绿/Light Green', 'hex': '#8BC34A'},
  ];

  @override
  Widget build(BuildContext context) {
    return ListView.builder(
      itemCount: colors.length,
      itemBuilder: (context, index) {
        final color = colors[index];
        return ListTile(
          leading: CircleAvatar(
            backgroundColor: Color(int.parse(color['hex']!.substring(1, 7), radix: 16) + 0xFF000000),
          ),
          title: Text(color['name']!),
          subtitle: Text(color['hex']!),
          onTap: () {
            final selectedColor = Color(int.parse(color['hex']!.substring(1, 7), radix: 16) + 0xFF000000);
            Navigator.push(
              context,
              MaterialPageRoute(
                builder: (context) => ColorDetailScreen(
                  colorName: color['name']!,
                  colorHex: color['hex']!,
                  onColorSelected: onColorSelected,
                  baseColor: selectedColor,
                ),
              ),
            ).then((selectedShade) {
              if (selectedShade != null) {
                onColorSelected(selectedShade);
              }
            });
          },
        );
      },
    );
  }
}

class ColorDetailScreen extends StatelessWidget {
  final String colorName;
  final String colorHex;
  final Function(Color) onColorSelected;
  final Color baseColor;

  ColorDetailScreen({
    required this.colorName,
    required this.colorHex,
    required this.onColorSelected,
    required this.baseColor,
  });

  List<Color> _getShades(Color color) {
    return [
      color.withOpacity(0.1),
      color.withOpacity(0.2),
      color.withOpacity(0.3),
      color.withOpacity(0.4),
      color.withOpacity(0.5),
      color.withOpacity(0.6),
      color.withOpacity(0.7),
      color.withOpacity(0.8),
      color.withOpacity(0.9),
      color.withOpacity(1.0),
    ];
  }

  @override
  Widget build(BuildContext context) {
    final List<Color> shades = _getShades(baseColor);

    return Scaffold(
      appBar: AppBar(
        title: Text(colorName),
        backgroundColor: baseColor,
      ),
      body: ListView.builder(
        itemCount: shades.length,
        itemBuilder: (context, index) {
          final shade = shades[index];
          return ListTile(
            tileColor: shade,
            title: Center(
              child: Text(
                '${(shade.opacity * 100).toInt()}%\n${shade.toString().toUpperCase()}',
                textAlign: TextAlign.center,
                style: TextStyle(color: Colors.white),
              ),
            ),
            onTap: () {
              Navigator.pop(context, shade);
            },
          );
        },
      ),
    );
  }
}
