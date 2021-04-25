using System.Collections;
using System.Collections.Generic;
using UnityEngine;

[CreateAssetMenu(fileName = "BoardMap", menuName = "ScriptableObjects/BoardMap", order = 1)]
public class BoardMapScriptableObject : ScriptableObject
{

    [System.Serializable]
    public class BoardMapConfig
    {
        public StationConfig[] stations;
        public ConnectionConfig[] connections;
    }

    [System.Serializable]
    public class StationConfig
    {
        public int stationId;
        public float locationX;
        public float locationY;
    }

    [System.Serializable]
    public class ConnectionConfig
    {
        public int sourceStationId;
        public string transportationType;
        public int targetStationId;
    }
    
    public BoardMapConfig BoardMap { get; private set; }
    public TextAsset BoardMapConfigJsonFile;

    void Awake()
    {
        BoardMap = JsonUtility.FromJson<BoardMapConfig>(BoardMapConfigJsonFile.text);
        
        foreach (StationConfig station in BoardMap.stations)
        {
            Debug.Log("Found station " + station.stationId + ": x= " + station.locationX + ", y=" + station.locationY);
        }
    }

}
