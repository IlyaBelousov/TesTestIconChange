import React, {useState} from 'react';
import {
  Image,
  Text,
  View,
  StyleSheet,
  TouchableWithoutFeedback,
  SafeAreaView,
  NativeModules,
  Platform,
} from 'react-native';

const App = () => {
  const [currentIcon, setCurrentIcon] = useState('');
  const {ChangeIconModule} = NativeModules;

  const changeAppIcon = async name => {
    try {
      ChangeIconModule.changeIcon(name);
      setCurrentIcon(name);
    } catch (err) {}
  };

  const iconsList = [
    {
      id: 1,
      iconName: 'Trashred',
      image: require('./assets/appIcons/Trashred.png'),
    },
    {
      id: 2,
      iconName: 'Trashtransp',
      image: require('./assets/appIcons/Trashtransp.png'),
    },
  ];

  return (
    <SafeAreaView style={styles.container}>
      <View style={styles.headingContainer}>
        <Text style={styles.heading}>Select your App Icon</Text>
      </View>
      <View style={styles.iconContainer}>
        {iconsList.map(icon => {
          const isSelected = icon.iconName === currentIcon;
          return (
            <TouchableWithoutFeedback
              key={icon.id}
              onPress={() => {
                changeAppIcon(icon?.iconName);
              }}>
              <View
                style={[
                  styles.iconView,
                  {
                    backgroundColor: isSelected ? 'lightgray' : 'transparent',
                  },
                ]}>
                <Image style={styles.icon} source={icon.image} />
              </View>
            </TouchableWithoutFeedback>
          );
        })}
      </View>
      <Text>Current Icon : {currentIcon}</Text>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  container: {flex: 1},
  headingContainer: {padding: 8, marginTop: 20},
  heading: {
    fontWeight: '500',
    fontSize: 20,
    color: '#000',
    textAlign: 'center',
    marginBottom: 16,
  },
  iconContainer: {
    flex: 1,
    alignItems: 'center',
    flexDirection: 'row',
    flexWrap: 'wrap',
    justifyContent: 'center',
  },
  iconView: {
    borderRadius: 10,
    margin: 4,
  },
  icon: {
    height: 100,
    borderRadius: 10,
    width: 100,
    margin: 8,
  },
});

export default App;
