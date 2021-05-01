using System;
using System.Collections.Generic;
using UnityEngine;

public class StationNetworkManager : MonoBehaviour
{
    [System.Serializable]
    private class BoardMapConfig
    {
        public StationConfig[] stations;
        public ConnectionConfig[] connections;
    }

    [System.Serializable]
    private class StationConfig
    {
        public int stationId;
        public float locationX;
        public float locationY;
    }

    [System.Serializable]
    private class ConnectionConfig
    {
        public int sourceStationId;
        public string transportationType;
        public int targetStationId;
    }

    private const string BOARD_MAP_FOLDER = "GameBoard/";
    private const float PIXELS_PER_UNIT = 100f;

    public Dictionary<int, Station> stationLookup = new Dictionary<int, Station>();
    [SerializeField] private GameObject stationStationPrefab;

    public void Awake()
    {
        TextAsset stationNetworkConfigFile = Resources.Load<TextAsset>(BOARD_MAP_FOLDER + "station_network");
        BoardMapConfig boardMapConfig = JsonUtility.FromJson<BoardMapConfig>(stationNetworkConfigFile.text);

        SpriteRenderer boardBackgroundImageSpriteRenderer = gameObject.GetComponentInParent<SpriteRenderer>();

        float mapWidth = boardBackgroundImageSpriteRenderer.sprite.bounds.size.x * PIXELS_PER_UNIT;
        float mapHeight = boardBackgroundImageSpriteRenderer.sprite.bounds.size.y * PIXELS_PER_UNIT;
        float offsetX = 22f;
        float offsetY = -54f;

        foreach (StationConfig stationConfig in boardMapConfig.stations)
        {
            GameObject stationPrefab = Instantiate(
                stationStationPrefab, 
                new Vector3((stationConfig.locationX - mapWidth / 2f + offsetX) / PIXELS_PER_UNIT, (mapHeight / 2f - stationConfig.locationY + offsetY) / PIXELS_PER_UNIT, gameObject.transform.position.z), 
                Quaternion.identity);
            stationPrefab.name = "Station_" + stationConfig.stationId;
            stationPrefab.transform.parent = gameObject.transform;
            Station station = stationPrefab.GetComponent<Station>();

            station.stationId = stationConfig.stationId;
            stationLookup.Add(stationConfig.stationId, stationPrefab.GetComponent<Station>());
        }

        foreach (ConnectionConfig connectionConfig in boardMapConfig.connections)
        {
            if (stationLookup.ContainsKey(connectionConfig.sourceStationId) &&
                stationLookup.ContainsKey(connectionConfig.targetStationId))
            {
                Station srcStation = stationLookup[connectionConfig.sourceStationId];
                Station tgtStation = stationLookup[connectionConfig.targetStationId];

                Enum.TryParse(connectionConfig.transportationType, true, out TransportationType transportationType);

                Connection srcConnection = srcStation.gameObject.AddComponent<Connection>();
                srcConnection.targetStation = tgtStation;
                srcConnection.transportationType = transportationType;

                Connection tgtConnection = tgtStation.gameObject.AddComponent<Connection>();
                tgtConnection.targetStation = srcStation;
                tgtConnection.transportationType = transportationType;

                srcStation.connections.Add(srcConnection);
                tgtStation.connections.Add(tgtConnection);
            }
        }
    }

    // Start is called before the first frame update
    private void Start()
    {
    }

    // Update is called once per frame
    private void Update()
    {
    }
}